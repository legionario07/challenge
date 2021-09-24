package br.com.challenge.presentation.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.challenge.BaseTest
import br.com.challenge.domain.repository.local.ListRepoRepositoryLocalImpl
import br.com.challenge.domain.repository.remote.IListRepoRepository
import br.com.challenge.domain.repository.remote.ListRepoRepositoryImpl
import br.com.challenge.domain.usecases.ListRepoUseCase
import io.mockk.*
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import java.lang.Exception

@ExperimentalCoroutinesApi
class ListRepoViewModelTest : BaseTest() {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var listRepoViewModel: ListRepoViewModel
    private lateinit var listRepoUseCase: ListRepoUseCase
    private lateinit var listRepoStateObserver: Observer<ListRepoState>

    private lateinit var remoteRepositoryImpl: IListRepoRepository
    private lateinit var localRepositoryImpl: IListRepoRepository

    val repositoriesEntities = getRepositoriesEntitiesDTOFake()

    companion object {
        const val MESSAGE_ERROR = "GENERIC ERROR"
    }

    @Before
    fun init() {
        super.setUp()
        remoteRepositoryImpl = spyk(ListRepoRepositoryImpl(apiFake))
        localRepositoryImpl = spyk(ListRepoRepositoryLocalImpl(apiDaoFake))

        listRepoUseCase = spyk(ListRepoUseCase(remoteRepositoryImpl, localRepositoryImpl))

        listRepoViewModel = ListRepoViewModel(listRepoUseCase)
    }

    @Test
    fun `getRepositories - Deve retonar o observer ItemsRepositories quando sucesso`() =
        runBlocking {
            val stateExpected = ListRepoState.ItemsRepositories(repositoriesEntities)

            listRepoViewModel.getRepositories(language, sort, page)

            delay(1000)

            coVerify { listRepoUseCase.getRepositories(language, sort, page) }
            Assert.assertEquals(listRepoViewModel.listRepoMutable.value, stateExpected)
        }

    @Test
    fun `getRepositories - Deve retonar o Failure quando erro`() =
        runBlocking {

            val exception = Exception(MESSAGE_ERROR)

            coEvery { listRepoUseCase.getRepositories(language, sort, page) } throws exception

            listRepoViewModel.getRepositories(language, sort, page)

            coVerify { listRepoUseCase.getRepositories(language, sort, page) }
        }

    @After
    fun close() {
        super.tearDown()
    }
}