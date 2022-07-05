package com.wonmirzo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wonmirzo.R
import com.wonmirzo.model.Poster
import com.wonmirzo.network.volley.VolleyHandler
import com.wonmirzo.network.volley.VolleyHttp
import com.wonmirzo.utils.Logger

class MainActivity : AppCompatActivity() {
     val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        val poster = Poster(2, "Mirzohid", 1750, 19, "")
        // get all posts
        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), object : VolleyHandler {

            override fun onSuccess(response: String?) {
                Logger.d(TAG, response!!)
            }

            override fun onError(error: String?) {
                Logger.d(TAG, error!!)
            }
        })
        // get single post
        VolleyHttp.getSinglePost(
            VolleyHttp.API_SINGLE_POST + poster.id,
            VolleyHttp.paramsEmpty(),
            object : VolleyHandler {

                override fun onSuccess(response: String?) {
                    Logger.d(TAG, response!!)
                }

                override fun onError(error: String?) {
                    Logger.d(TAG, error!!)
                }
            })
        // create a post
        VolleyHttp.post(VolleyHttp.API_CREATE_POST, VolleyHttp.paramsCreate(poster),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    Logger.d(TAG, response!!)
                }

                override fun onError(error: String?) {
                    Logger.d(TAG, error!!)
                }
            })

        // update the post
        VolleyHttp.put(VolleyHttp.API_UPDATE_POST + poster.id, VolleyHttp.paramsUpdate(poster),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    Logger.d(TAG, response!!)
                }

                override fun onError(error: String?) {
                    Logger.d(TAG, error!!)
                }
            })
        // delete the post
        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id, object : VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d(TAG, response!!)
            }

            override fun onError(error: String?) {
                Logger.d(TAG, error!!)
            }
        })

    }

}