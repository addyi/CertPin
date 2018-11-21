package de.eosn.certpin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var requestResultTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestResultTextView = findViewById(R.id.request_result)

        findViewById<Button>(R.id.unsecured_request).setOnClickListener { unsecuredRequest() }
        findViewById<Button>(R.id.pinned_request).setOnClickListener { pinnedRequest() }
    }

    private fun unsecuredRequest() {
        val okHttpClient = OkHttpClient.Builder().build()

        executeApiRequestWithOkHttpClient(okHttpClient)
    }

    private fun pinnedRequest() {
        val certificatePinner = CertificatePinner.Builder()
            .add("api.github.com", "sha256/y2HhTRXXLdmAF1esYBb/muQUl3BIBdmEB8jUvMrGc28=")
            .build()

        val okHttpClient = OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .build()

        executeApiRequestWithOkHttpClient(okHttpClient)
    }

    private fun executeApiRequestWithOkHttpClient(okHttpClient: OkHttpClient) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val gitHubService = retrofit.create(GitHubService::class.java)

        gitHubService.getGitHubUser("addyi").enqueue(object : Callback<GitHubUser> {
            override fun onFailure(call: Call<GitHubUser>, t: Throwable) {
                Log.d(tag, "onFailure", t)

                requestResultTextView?.text = t.localizedMessage
            }

            override fun onResponse(call: Call<GitHubUser>, response: Response<GitHubUser>) {
                if (response.isSuccessful) {
                    Log.d(tag, "GithubUser: ${response.body()}")

                    requestResultTextView?.text = response.body().toString()
                } else {
                    Log.d(tag, "Response but failed: ${response.errorBody()}")

                    requestResultTextView?.text = response.errorBody().toString()
                }
            }

        })
    }

}
