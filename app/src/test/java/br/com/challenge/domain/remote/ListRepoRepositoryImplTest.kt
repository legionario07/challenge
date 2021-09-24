package br.com.challenge.domain.remote

import br.com.challenge.BaseTest
import br.com.challenge.data.dto.RepositoryDTO
import br.com.challenge.domain.repository.remote.ListRepoRepositoryImpl
import io.mockk.coVerify
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.collect
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ListRepoRepositoryImplTest : BaseTest() {

    private lateinit var repoRepositoryImpl: ListRepoRepositoryImpl

    @Before
    fun init() {
        super.setUp()

        repoRepositoryImpl = ListRepoRepositoryImpl(apiFake)
    }

    @Test
    fun `getRepositories - Deve retornar um RepositoryDTO quando sucesso`() = runBlocking {
        var repositoryDTO: RepositoryDTO? = null
        repoRepositoryImpl.getRepositories(language, sort, page)
            .collect {
                repositoryDTO = it
            }

        coVerify { apiFake.getRepositories(language, sort, page) }

        Assert.assertEquals(repositoryDTO?.items?.size, SIZE_LIST_REPOSITORY_DTO_RESPONSE)
    }

    @Test
    fun `saveRepository - Deve salvar um RepositoryDTO quando sucesso`() = runBlocking {

        val repoCapture = slot<RepositoryDTO>()
        val repositoryDTO = getRepositoryDTOFake()

        repoRepositoryImpl.saveRepository(repositoryDTO)
        coVerify { apiFake.saveRepository(capture(repoCapture)) }

        Assert.assertEquals(repoCapture.captured.items.size, repositoryDTO.items.size)
    }

    @After
    fun close(){
        super.tearDown()
    }
}