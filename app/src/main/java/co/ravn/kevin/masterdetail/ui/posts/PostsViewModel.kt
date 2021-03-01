package co.ravn.kevin.masterdetail.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ravn.kevin.masterdetail.model.Post
import co.ravn.kevin.masterdetail.model.Result
import co.ravn.kevin.masterdetail.networking.Api
import javax.inject.Inject

class PostsViewModel @Inject constructor(private val api: Api) : ViewModel() {
    suspend fun getPosts(): Result<List<Post>> {
        return api.getPosts()
    }
}