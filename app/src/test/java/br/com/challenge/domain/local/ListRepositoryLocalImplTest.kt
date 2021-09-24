package br.com.challenge.domain.local

import br.com.challenge.BaseTest
import br.com.challenge.data.dto.RepositoryDTO
import br.com.challenge.domain.repository.local.ListRepoRepositoryLocalImpl
import io.mockk.coVerify
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ListRepositoryLocalImplTest : BaseTest() {

    private lateinit var repoRepositoryLocalImpl: ListRepoRepositoryLocalImpl

    @Before
    fun init() {
        super.setUp()

        repoRepositoryLocalImpl = ListRepoRepositoryLocalImpl(apiDaoFake)
    }

    @Test
    fun `getRepositories - Deve retornar um RepositoryDTO Local quando sucesso`() = runBlocking {
        var repositoryDTO: RepositoryDTO? = null

        apiDaoFake.insert(getRepositoryDTOFake())

        repoRepositoryLocalImpl.getRepositories(language, sort, page)
            .collect {
                repositoryDTO = it
            }

        coVerify { apiDaoFake.getRepository(page) }

        Assert.assertEquals(repositoryDTO?.items?.size, SIZE_LIST_REPOSITORY_DTO_RESPONSE)
    }

    @Test
    fun `getRepositories - Deve retornar um RepositoryDTO nulo quando nao existir`() = runBlocking {
        var repositoryDTO: RepositoryDTO? = null

        repoRepositoryLocalImpl.getRepositories(language, sort, page)
            .collect {
                repositoryDTO = it
            }

        coVerify { apiDaoFake.getRepository(page) }

        Assert.assertNull(repositoryDTO)
    }

    @Test
    fun `saveRepository - Deve salvar um RepositoryDTO no Local quando sucesso`() = runBlocking {

        val repoCapture = slot<RepositoryDTO>()
        val repositoryDTO = getRepositoryDTOFake()

        repoRepositoryLocalImpl.saveRepository(repositoryDTO)
        coVerify { apiDaoFake.insert(capture(repoCapture)) }

        Assert.assertEquals(repoCapture.captured.id, repositoryDTO.id)
    }

    @After
    fun close(){
        super.tearDown()
    }
}