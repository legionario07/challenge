package br.com.challenge

import br.com.challenge.domain.local.FakeRepoDao
import br.com.challenge.domain.remote.FakeRepoApi
import br.com.challenge.domain.repository.local.ListRepoRepositoryLocalImpl
import br.com.challenge.domain.repository.remote.ListRepoRepositoryImpl
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
        var remoteRepositoryImpl = spyk(ListRepoRepositoryImpl(apiFake))
        var localRepositoryImpl = spyk(ListRepoRepositoryLocalImpl(apiDaoFake))
    }

    private val testDispatcher = TestCoroutineDispatcher()

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