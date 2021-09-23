package br.com.challenge.presentation.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.challenge.databinding.FragmentListRepoBinding
import br.com.challenge.domain.repository.api.ApiManager
import br.com.challenge.domain.repository.remote.ListRepoRepositoryImpl
import br.com.challenge.domain.usecases.ListRepoUseCase
import br.com.challenge.presentation.BaseFragment
import kotlinx.coroutines.flow.collect

class ListRepoFragment : BaseFragment() {

    private var _binding: FragmentListRepoBinding? = null
    private val binding get() = _binding!!

    private val viewModel =
        ListRepoViewModel(ListRepoUseCase(ListRepoRepositoryImpl(ApiManager.repositoryApi)))
    private lateinit var listRepoAdapter: ListRepoAdapter

    private var isLoading = false
    private var page = 1;

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
            listRepoAdapter = ListRepoAdapter(mutableListOf())
            recyclerListRepo.adapter = listRepoAdapter

            recyclerListRepo.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                    if (dy > 0) {
                        val visibleItemCount = layoutManager.childCount
                        val totalItemCount = layoutManager.itemCount
                        val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
                        if (!isLoading) {
                            if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                                isLoading = true
                                viewModel.getRepositories(page = ++page)
                                isLoading = false
                            }
                        }
                    }
                }
            })
        }
    }

    private fun configObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.listRepoMutable.collect {
                when (it) {
                    is ListRepoState.ItemsRepositories -> {
                        listRepoAdapter.updateList(it.repositories)
                        listRepoAdapter.notifyDataSetChanged()

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
