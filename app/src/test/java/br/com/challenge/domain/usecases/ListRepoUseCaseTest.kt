package br.com.challenge.domain.usecases

import br.com.challenge.BaseTest
import br.com.challenge.domain.local.FakeRepoDao
import br.com.challenge.domain.remote.FakeRepoApi
import br.com.challenge.domain.repository.local.ListRepoRepositoryLocalImpl
import br.com.challenge.domain.repository.remote.IListRepoRepository
import br.com.challenge.domain.repository.remote.ListRepoRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ListRepoUseCaseTest : BaseTest() {

    private lateinit var listRepoUseCase: ListRepoUseCase

    private lateinit var remoteRepositoryImpl: IListRepoRepository
    private lateinit var localRepositoryImpl: IListRepoRepository

    @Before
    fun init() {
        super.setUp()

        remoteRepositoryImpl = spyk(ListRepoRepositoryImpl(apiFake))
        localRepositoryImpl = spyk(ListRepoRepositoryLocalImpl(apiDaoFake))

        listRepoUseCase = ListRepoUseCase(remoteRepositoryImpl, localRepositoryImpl)
    }

    @Test
    fun `getRepositories - Deve retonar uma lista de Repositorios do local quando existir no local`() =
        runBlocking {

            val repositoryDTOFake = getRepositoryDTOFake()
            apiDaoFake.insert(repositoryDTOFake)

            listRepoUseCase.getRepositories(language, sort, page)

            coVerify { localRepositoryImpl.getRepositories(language, sort, page) }
            coVerify(exactly = 0) { remoteRepositoryImpl.getRepositories(language, sort, page) }
        }

    @Test
    fun `getRepositories - Deve retonar uma lista de Repositorios remoto quando não existir no local e salvar no local em seguida`() =
        runBlocking {

            remoteRepositoryImpl = spyk(ListRepoRepositoryImpl(FakeRepoApi()))
            localRepositoryImpl = spyk(ListRepoRepositoryLocalImpl(FakeRepoDao()))

            listRepoUseCase = ListRepoUseCase(remoteRepositoryImpl, localRepositoryImpl)

            val repositoryDTOFake = getRepositoryDTOFake()
            coEvery { apiFake.getRepositories(language, sort, page) } returns repositoryDTOFake

            listRepoUseCase.getRepositories(language, sort, page)

            coVerifyOrder {
                localRepositoryImpl.getRepositories(language, sort, page)
                remoteRepositoryImpl.getRepositories(language, sort, page)
                localRepositoryImpl.saveRepository(repositoryDTOFake)
            }
        }

    @After
    fun close() {
        super.tearDown()
    }
}
