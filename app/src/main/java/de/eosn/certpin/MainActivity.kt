package de.eosn.certpin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val gitHubService = retrofit.create(GitHubService::class.java)

        gitHubService.getGitHubUser("addyi").enqueue(object : Callback<GitHubUser> {
            override fun onFailure(call: Call<GitHubUser>, t: Throwable) {
                Log.d(tag, "onFailure", t)
            }

            override fun onResponse(call: Call<GitHubUser>, response: Response<GitHubUser>) {
                if (response.isSuccessful) {
                    Log.d(tag, "GithubUser: ${response.body()}")
                } else {
                    Log.d(tag, "Response but failed: ${response.errorBody()}")
                }
            }

        })

    }

}
