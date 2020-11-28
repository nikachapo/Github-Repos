package com.example.githubrepos.ui.search_repos.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.githubrepos.databinding.ActivityRepoDetailsBinding
import com.example.githubrepos.model.Repo

class RepoDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepoDetailsBinding

    private var repo: Repo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repo = intent.getParcelableExtra(EXTRA_REPO)

        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            title = repo?.name
        }

        repo?.let {
            binding.repoFullNameTV.text = it.fullName
            binding.repoDescTV.text = it.description
            binding.repoForksTV.text = it.forks.toString()
            binding.repoUrlTV.text = it.url
            binding.repoUrlTV.setOnClickListener { _ ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_REPO = "ex-repo"
    }
}