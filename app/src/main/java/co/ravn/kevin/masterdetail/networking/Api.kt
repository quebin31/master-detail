package co.ravn.kevin.masterdetail.networking

import co.ravn.kevin.masterdetail.model.*

class Api(private val service: ApiService) {
    suspend fun getPosts(): Result<List<Post>> = runToResult {
        service.getPosts()
    }

    suspend fun getPostDetails(postId: String): Result<PostDetails> = runToResult {
        val post = service.getPost(postId)
        val comments = service.getComments(postId)
        PostDetails(post, comments)
    }
}