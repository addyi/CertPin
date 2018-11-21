package de.eosn.certpin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
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

        val certificatePinner = CertificatePinner.Builder()
            .add("api.github.com", "sha256/y2HhTRXXLdmAF1esYBb/muQUl3BIBdmEB8jUvMrGc28=")
            .build()

        val okHttpClient = OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
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
