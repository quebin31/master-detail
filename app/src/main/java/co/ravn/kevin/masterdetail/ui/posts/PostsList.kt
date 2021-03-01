package co.ravn.kevin.masterdetail.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.ravn.kevin.masterdetail.databinding.ListItemPostBinding
import co.ravn.kevin.masterdetail.model.Post


typealias ClickListener = (Post) -> Unit

class PostAdapter(private val onClick: ClickListener) : RecyclerView.Adapter<PostViewHolder>() {
    private val posts = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemPostBinding.inflate(layoutInflater, parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position], onClick)
    }

    override fun getItemCount(): Int = posts.size

    fun updateData(newPosts: List<Post>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }
}

class PostViewHolder(private val binding: ListItemPostBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post, onClick: ClickListener) {

    }
}