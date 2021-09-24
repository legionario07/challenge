package br.com.challenge.presentation.repo

import br.com.challenge.data.entity.LanguageType
import br.com.challenge.data.entity.SortType
import br.com.challenge.domain.usecases.ListRepoUseCase
import br.com.challenge.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListRepoViewModel @Inject constructor(private val listRepoUseCase: ListRepoUseCase) : BaseViewModel() {

    private val _listRepoMutable: MutableStateFlow<ListRepoState> =
        MutableStateFlow(ListRepoState.Empty)
    val listRepoMutable: StateFlow<ListRepoState> = _listRepoMutable

    fun getRepositories(
        language: String = LanguageType.KOTLIN.value,
        sort: String = SortType.STARS.value,
        page: Int = 1
    ) {

        _listRepoMutable.value = ListRepoState.Loading(true)

        scopeJob.launch {
            listRepoUseCase.getRepositories(
                language,
                sort,
                page
            )
                .catch { e ->
                    handlerError(e as Exception)
                }.collect {
                    _listRepoMutable.value = ListRepoState.ItemsRepositories(it!!)
                }
        }
    }

    private fun handlerError(exception: Exception) {
        _listRepoMutable.value = exception.message?.let { ListRepoState.Failure(it) }!!
    }
}