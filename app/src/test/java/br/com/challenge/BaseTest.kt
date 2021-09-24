package br.com.challenge

import br.com.challenge.data.dto.RepositoryDTO
import br.com.challenge.data.entity.RepositoryEntity
import br.com.challenge.domain.local.FakeRepoDao
import br.com.challenge.domain.remote.FakeRepoApi
import br.com.challenge.domain.utils.ConstantsUtil
import br.com.challenge.domain.utils.FileUtil
import br.com.challenge.domain.utils.LoggerUtil
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@ExperimentalCoroutinesApi
open class BaseTest {

    companion object {
        //objects
        var apiFake = spyk(FakeRepoApi())
        var apiDaoFake = spyk(FakeRepoDao())

        const val language = "language:kotlin"
        const val sort = "stars"
        const val page = 1

        const val SIZE_LIST_REPOSITORY_DTO_RESPONSE = 30
    }

    private val testDispatcher = TestCoroutineDispatcher()

    fun getRepositoryDTOFake(): RepositoryDTO {
        val file = FileUtil.readFile(ConstantsUtil.JSON_REPOSITORY_DTO)
        return FileUtil.getFromReader(file, ConstantsUtil.REPOSITORY_DTO_TYPE) as RepositoryDTO
    }

    fun getRepositoriesEntitiesDTOFake(): List<RepositoryEntity> {
        val file = FileUtil.readFile(ConstantsUtil.JSON_REPOSITORY_ENTITIES)
        return FileUtil.getFromReader(file, ConstantsUtil.REPOSITORY_ENTITIES_TYPE) as List<RepositoryEntity>
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this, relaxed = true)
        LoggerUtil.disableLogForTesting()
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}