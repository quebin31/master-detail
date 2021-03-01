package co.ravn.kevin.masterdetail.networking

import co.ravn.kevin.masterdetail.model.Comment
import co.ravn.kevin.masterdetail.model.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>

    @GET("/posts")
    suspend fun getPost(@Query("id") postId: Int): List<Post>

    @GET("/comments")
    suspend fun getComments(@Query("postId") postId: Int): List<Comment>
}