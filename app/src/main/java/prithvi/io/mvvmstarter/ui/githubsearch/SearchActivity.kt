package prithvi.io.mvvmstarter.ui.githubsearch

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.InputFilter
import kotlinx.android.synthetic.main.activity_github.*
import prithvi.io.mvvmstarter.R
import prithvi.io.mvvmstarter.data.models.Response
import prithvi.io.mvvmstarter.ui.base.BaseActivity
import prithvi.io.mvvmstarter.utility.extentions.addTextWatcher
import prithvi.io.mvvmstarter.utility.extentions.getViewModel
import prithvi.io.mvvmstarter.utility.extentions.observe
import prithvi.io.mvvmstarter.utility.extentions.visible
import prithvi.io.mvvmstarter.viewmodel.ViewModelFactory
import javax.inject.Inject

class SearchActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: SearchViewModel
    private lateinit var mAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github)

        viewModel = getViewModel(SearchViewModel::class.java, viewModelFactory)

        mAdapter = SearchAdapter()
        rvGithubUsers.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(object : DividerItemDecoration(context, VERTICAL) {})
            adapter = mAdapter
        }

        ivClear.setOnClickListener { etSearch.setText("") }
        etSearch.apply {
            filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
                if (source != null && source.matches("[^a-zA-Z0-9 -]".toRegex())) return@InputFilter ""
                return@InputFilter null
            })
            addTextWatcher(
                    afterTextChange = {
                        when {
                            !it.isNullOrBlank() && it!!.length > 1 -> viewModel.getGithubUsers(it.toString())
                            else -> {
                                mAdapter.githubUsers = listOf()
                                pbLoading.visible = false
                                ivClear.visible = true
                                rvGithubUsers.visible = false
                            }
                        }
                    })
        }

        observe(viewModel.githubUser) {
            it ?: return@observe
            when (it.status) {
                Response.Status.LOADING -> {
                    loadingView.showContent()
                    pbLoading.visible = true
                    ivClear.visible = false
                    rvGithubUsers.visible = true
                }
                Response.Status.SUCCESS -> {
                    it.data ?: return@observe
                    pbLoading.visible = false
                    ivClear.visible = true
                    when (it.data.isEmpty()) {
                        true -> {
                            mAdapter.githubUsers = listOf()
                            loadingView.showEmpty(R.drawable.ic_hourglass_empty_black_24dp, "No user", "No user found with the name ${etSearch.text}")
                        }
                        false -> {
                            loadingView.showContent()
                            rvGithubUsers.visible = true
                            mAdapter.githubUsers = if (etSearch.text.toString() != "") it.data else listOf()
                        }
                    }
                }
                Response.Status.ERROR -> {
                    loadingView.showEmpty(R.drawable.ic_search_again_black_24dp, "Not good", "Some error occurred. Please try to search again.")
                    mAdapter.githubUsers = listOf()
                    pbLoading.visible = false
                    ivClear.visible = true
                }
            }
        }
    }
}
