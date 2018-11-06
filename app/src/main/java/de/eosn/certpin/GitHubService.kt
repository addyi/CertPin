package de.eosn.certpin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{username}")
    fun getGitHubUser(@Path("username") username: String): Call<GitHubUser>
}