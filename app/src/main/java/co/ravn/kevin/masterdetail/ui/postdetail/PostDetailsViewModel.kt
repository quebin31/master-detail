package co.ravn.kevin.masterdetail.ui.postdetail

import androidx.lifecycle.ViewModel
import co.ravn.kevin.masterdetail.model.PostDetails
import co.ravn.kevin.masterdetail.model.Result
import co.ravn.kevin.masterdetail.networking.Api
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(private val api: Api) : ViewModel() {

    suspend fun getPostDetails(postId: Int): Result<PostDetails> = api.getPostDetails(postId)
}