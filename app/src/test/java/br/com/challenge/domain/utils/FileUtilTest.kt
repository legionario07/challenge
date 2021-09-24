package br.com.challenge.domain.utils

import br.com.challenge.BaseTest
import br.com.challenge.data.dto.RepositoryDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FileUtilTest : BaseTest() {

    @Before
    fun init() {
        super.setUp()
    }

    @Test
    fun `readFile - Deve retonar um objeto Reader quando sucesso`() {
        val readFile = FileUtil.readFile(ConstantsUtil.JSON_REPOSITORY_DTO)

        Assert.assertNotNull(readFile)
    }

    @Test
    fun `getFromReader - Deve retonar um objeto quando sucesso`() {
        val readFile = FileUtil.readFile(ConstantsUtil.JSON_REPOSITORY_DTO)

        val repositoryDTO =
            FileUtil.getFromReader(readFile, ConstantsUtil.REPOSITORY_DTO_TYPE) as RepositoryDTO

        Assert.assertNotNull(repositoryDTO)
    }

    @After
    fun close() {
        super.tearDown()
    }

}