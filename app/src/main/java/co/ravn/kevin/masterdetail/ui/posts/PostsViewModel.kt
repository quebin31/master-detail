package co.ravn.kevin.masterdetail.ui.posts

import androidx.lifecycle.ViewModel
import co.ravn.kevin.masterdetail.model.Post
import co.ravn.kevin.masterdetail.model.Result
import co.ravn.kevin.masterdetail.networking.Api
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val api: Api) : ViewModel() {

    suspend fun getPosts(): Result<List<Post>> = api.getPosts()
}