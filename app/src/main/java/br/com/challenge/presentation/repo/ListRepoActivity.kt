package br.com.challenge.presentation.repo

import android.os.Bundle
import br.com.challenge.R
import br.com.challenge.databinding.ActivityListRepoBinding
import br.com.challenge.presentation.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListRepoActivity : BaseActivity() {

    private lateinit var binding: ActivityListRepoBinding

    @Inject
    lateinit var listRepoFragment: ListRepoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val frameContainer = R.id.frame_container
        if(!frameContainer.hasCurrentFragment()) {
            setCurrentFragment(frameContainer, listRepoFragment)
        }
    }
}