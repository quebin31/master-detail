package co.ravn.kevin.masterdetail.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.ravn.kevin.masterdetail.databinding.FragmentPostsBinding
import co.ravn.kevin.masterdetail.model.Post
import co.ravn.kevin.masterdetail.model.Result
import co.ravn.kevin.masterdetail.utils.gone
import co.ravn.kevin.masterdetail.utils.toast
import co.ravn.kevin.masterdetail.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<PostsViewModel>()

    private val adapter by lazy {
        PostAdapter { navigateToPostDetails(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        postsList.adapter = adapter
        postsList.layoutManager = LinearLayoutManager(requireContext())
        getAllPosts()
    }

    private fun getAllPosts() {
        binding.noData.gone()
        binding.progress.visible()

        lifecycleScope.launch {
            val result = viewModel.getPosts()
            binding.progress.gone()

            when (result) {
                is Result.Ok -> {
                    val posts = result.value
                    if (posts.isEmpty()) {
                        binding.noData.visible()
                    } else {
                        adapter.updateData(posts)
                    }
                }

                is Result.Err<*> -> {
                    binding.noData.visible()
                    context?.toast("Failed to get posts")
                }
            }
        }
    }

    private fun navigateToPostDetails(post: Post) {
        val directions = PostsFragmentDirections.actionShowPostDetail(post.id)
        findNavController().navigate(directions)
    }

}
