package co.ravn.kevin.masterdetail.ui.postdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.ravn.kevin.masterdetail.databinding.ListItemCommentBinding
import co.ravn.kevin.masterdetail.model.Comment

class CommentAdapter : RecyclerView.Adapter<CommentViewHolder>() {
    private val comments = mutableListOf<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemCommentBinding.inflate(layoutInflater, parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount(): Int = comments.size

    fun updateData(newComments: List<Comment>){
        comments.clear()
        comments.addAll(newComments)
        notifyDataSetChanged()
    }
}

class CommentViewHolder(private val binding: ListItemCommentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(comment: Comment) {
        binding.title.text = comment.name
        binding.email.text = comment.email
        binding.body.text = comment.body
    }
}