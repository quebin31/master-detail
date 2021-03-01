package co.ravn.kevin.masterdetail.networking

import co.ravn.kevin.masterdetail.model.*

class Api(private val service: ApiService) {
    suspend fun getPosts(): Result<List<Post>> = runToResult {
        service.getPosts()
    }

    suspend fun getPostDetails(postId: Int): Result<PostDetails> = runToResult {
        val post = service.getPost(postId)[0]
        val comments = service.getComments(postId)
        PostDetails(post, comments)
    }
}