package br.com.challenge.domain.mapper

import br.com.challenge.BaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RepositoryMapperTest : BaseTest() {

    @Before
    fun init() {
        super.setUp()
    }

    @Test
    fun `transformerRepository - Deve converter um RepositoryDTO em uma lista de RepositoryEntity`() {
        val repositoryDTOFake = getRepositoryDTOFake()

        val repositoriesEntities = RepositoryMapper.transformerRepository(repositoryDTOFake)

        Assert.assertEquals(repositoryDTOFake.items.size, repositoriesEntities.size)
    }

    @After
    fun close() {
        super.tearDown()
    }
}