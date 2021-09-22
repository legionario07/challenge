package br.com.challenge.presentation.repo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.com.challenge.R
import br.com.challenge.databinding.ActivityListRepoBinding
import br.com.challenge.presentation.BaseActivity

class ListRepoActivity : BaseActivity() {

    private lateinit var binding: ActivityListRepoBinding

    private var listRepoFragment = ListRepoFragment()

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}