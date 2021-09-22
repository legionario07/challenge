package br.com.challenge.presentation.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.challenge.databinding.FragmentListRepoBinding
import br.com.challenge.domain.repository.api.ApiManager
import br.com.challenge.domain.repository.remote.ListRepoRepositoryImpl
import br.com.challenge.domain.usecases.ListRepoUseCase
import br.com.challenge.presentation.BaseFragment
import kotlinx.coroutines.flow.collect

class ListRepoFragment : BaseFragment() {

    private var _binding: FragmentListRepoBinding? = null
    private val binding get() = _binding!!

    private val viewModel = ListRepoViewModel(ListRepoUseCase(ListRepoRepositoryImpl(ApiManager.repositoryApi)))
    private lateinit var listRepoAdapter: ListRepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        configObservers()
        viewModel.getRepositories()
    }

    private fun initRecyclerView() {
        with(binding) {
            recyclerListRepo.layoutManager = LinearLayoutManager(context)
            recyclerListRepo.setHasFixedSize(true)
            listRepoAdapter = ListRepoAdapter(listOf())
            recyclerListRepo.adapter = listRepoAdapter
        }
    }

    private fun configObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.listRepoMutable.collect {
                when (it) {
                    is ListRepoState.ItemsRepositories -> {
                        listRepoAdapter.updateList(it.repositories)
                        binding.recyclerListRepo.adapter?.notifyDataSetChanged()

                        hideLoading(binding.progress)
                    }

                    is ListRepoState.Empty -> {
                        //Do Nothing
                    }

                    is ListRepoState.Failure -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }

                    is ListRepoState.Loading -> {
                        loadingManager(binding.progress, it.isShowDialog)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
