package prithvi.io.mvvmstarter.ui.githubsearch

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.holder_github_user.view.*
import prithvi.io.mvvmstarter.R
import prithvi.io.mvvmstarter.data.models.GithubUser
import prithvi.io.mvvmstarter.ui.base.BaseViewHolder
import prithvi.io.mvvmstarter.utility.extentions.inflate
import prithvi.io.mvvmstarter.utility.extentions.roundOff

class SearchAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    var githubUsers: List<GithubUser> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.holder_github_user))

    override fun getItemCount(): Int = githubUsers.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.bind()

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {

        override fun bind() {
            super.bind()
            val user = githubUsers[adapterPosition]
            itemView.apply {
                tvName.text = user.login
                tvScore.text = user.score.roundOff()

                Glide.with(context)
                        .setDefaultRequestOptions(RequestOptions().apply {
                            placeholder(R.color.colorGrayishLight)
                            error(R.color.colorGrayishLight)
                            circleCrop()
                            centerCrop()
                        })
                        .asBitmap()
                        .load(user.avatarUrl)
                        .into(ivProfile)
            }
        }
    }
}