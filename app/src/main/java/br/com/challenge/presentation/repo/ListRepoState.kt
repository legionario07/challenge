package br.com.challenge.presentation.repo

import br.com.challenge.data.entity.RepositoryEntity

sealed class ListRepoState {
    data class ItemsRepositories(val repositories: List<RepositoryEntity>) : ListRepoState()
    data class Loading(val isShowDialog: Boolean) : ListRepoState()
    object Empty : ListRepoState()
    data class Failure(val message: String) : ListRepoState()
}