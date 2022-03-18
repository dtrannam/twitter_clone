package com.codepath.apps.restclienttemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.codepath.apps.restclienttemplate.models.Tweet
import okhttp3.Headers
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler as JsonHttpResponseHandler1

class ComposeActivity : AppCompatActivity() {

    lateinit var etCompose: EditText
    lateinit var btnTweet: Button
    lateinit var client: TwitterClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_acitivity)

        etCompose = findViewById(R.id.etTweet)
        btnTweet = findViewById(R.id.btnTweet)
        client = TwitterApplication.getRestClient(this)

        // Handle submissions
        btnTweet.setOnClickListener {

            // Get content
            val tweetContent = etCompose.text.toString()

            // Validation
            if (tweetContent.isEmpty()) {
                Toast.makeText(this, "Empty Tweet", Toast.LENGTH_SHORT).show()
            } else if (tweetContent.length > 140) {
                Toast.makeText(this, "Over character limit", Toast.LENGTH_SHORT).show()
            } else {
                client.publishTweet(
                    tweetContent,
                    object : JsonHttpResponseHandler1() {
                        override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                            Log.e("ComposeActivity", "working")
                            val tweet = Tweet.fromJson(json.jsonObject)
                            val intent = Intent()
                            intent.putExtra("tweet", tweet)
                            setResult(RESULT_OK, intent)
                            finish()
                        }

                        override fun onFailure(
                            statusCode: Int,
                            headers: Headers?,
                            response: String?,
                            throwable: Throwable?
                        ) {
                            Log.e("ComposeActivity", throwable.toString())
                        }
                    }
                )
            }
                // Make API call to Twitter


        }
    }
}