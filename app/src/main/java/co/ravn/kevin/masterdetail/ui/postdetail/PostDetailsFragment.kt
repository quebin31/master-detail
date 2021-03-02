package co.ravn.kevin.masterdetail.ui.postdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import co.ravn.kevin.masterdetail.R
import co.ravn.kevin.masterdetail.databinding.FragmentPostDetailsBinding
import co.ravn.kevin.masterdetail.model.Result
import co.ravn.kevin.masterdetail.ui.utils.SpacingItemDecorator
import co.ravn.kevin.masterdetail.utils.gone
import co.ravn.kevin.masterdetail.utils.toast
import co.ravn.kevin.masterdetail.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {

    private var _binding: FragmentPostDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<PostDetailsFragmentArgs>()
    private val viewModel by viewModels<PostDetailsViewModel>()

    private val adapter by lazy { CommentAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        commentsList.adapter = adapter
        commentsList.layoutManager = LinearLayoutManager(requireContext())
        val spacing = requireContext().resources.getDimensionPixelSize(R.dimen.padding_standard)
        commentsList.addItemDecoration(SpacingItemDecorator(spacing))
        commentsList.isNestedScrollingEnabled = false
        getPostDetails(args.postId)
    }

    private fun getPostDetails(postId: Int) {
        binding.noData.gone()
        binding.progress.visible()

        lifecycleScope.launch {
            val result = viewModel.getPostDetails(postId)
            binding.progress.gone()

            when (result) {
                is Result.Ok -> {
                    val postDetails = result.value
                    binding.title.text = postDetails.post.title
                    binding.body.text = postDetails.post.body

                    if (postDetails.comments.isEmpty()) {
                        binding.noData.visible()
                    } else {
                        adapter.updateData(postDetails.comments)
                    }
                }

                is Result.Err<*> -> {
                    binding.noData.visible()
                    context?.toast("Failed to get comments")
                }
            }
        }
    }
}