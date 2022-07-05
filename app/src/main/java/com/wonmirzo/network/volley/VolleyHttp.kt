package com.wonmirzo.network.volley

import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.wonmirzo.MyApplication
import com.wonmirzo.model.Poster
import com.wonmirzo.utils.Logger
import org.json.JSONObject

class VolleyHttp {
    companion object {
        private val TAG = VolleyHttp::class.java.simpleName
        const val IS_TESTER = true
        private const val SERVER_DEVELOPMENT = "http://dummy.restapiexample.com/api/v1/"
        private const val SERVER_PRODUCTION = "http://dummy.restapiexample.com/api/v1/"

        fun server(url: String): String {
            if (IS_TESTER)
                return SERVER_DEVELOPMENT + url
            return SERVER_PRODUCTION + url
        }

        fun headers(): HashMap<String, String> {
            val headers = HashMap<String, String>()
            headers["Content-type"] = "application/json; charset=UTF-8"
            return headers
        }

        /**
         *  Request Method`s
         */

        fun get(api: String, params: HashMap<String, String>, volleyHandler: VolleyHandler) {
            val stringRequest = object : StringRequest(Method.GET, server(api),
                Response.Listener { response ->
                    Logger.d(TAG, response)
                    volleyHandler.onSuccess(response)
                },
                Response.ErrorListener { error ->
                    Logger.d(TAG, error.toString())
                    volleyHandler.onError(error.toString())
                }) {
                override fun getParams(): MutableMap<String, String> {
                    return params
                }
            }
            MyApplication.instance!!.addToRequestQueue(stringRequest)
        }

        fun getSinglePost(api: String, params: HashMap<String, String>, volleyHandler: VolleyHandler) {
            val stringRequest = object : StringRequest(Method.GET, server(api),
                Response.Listener { response ->
                    Logger.d(TAG, response)
                    volleyHandler.onSuccess(response)
                },
                Response.ErrorListener { error ->
                    Logger.d(TAG, error.toString())
                    volleyHandler.onError(error.toString())
                }) {
                override fun getParams(): MutableMap<String, String> {
                    return params
                }
            }
            MyApplication.instance!!.addToRequestQueue(stringRequest)
        }

        fun post(api: String, body: HashMap<String, Any>, volleyHandler: VolleyHandler) {
            val stringRequest = object : StringRequest(Method.POST, server(api),
                Response.Listener { response ->
                    Logger.d(TAG, response)
                    volleyHandler.onSuccess(response)
                },
                Response.ErrorListener { error ->
                    Logger.d(TAG, error.toString())
                    volleyHandler.onError(error.toString())
                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    return headers()
                }

                override fun getBody(): ByteArray {
                    return JSONObject(body as Map<*, *>).toString().toByteArray()
                }
            }
            MyApplication.instance!!.addToRequestQueue(stringRequest)
        }

        fun put(api: String, body: HashMap<String, Any>, volleyHandler: VolleyHandler) {
            val stringRequest = object : StringRequest(Method.PUT, server(api),
                Response.Listener { response ->
                    Logger.d(TAG, response)
                    volleyHandler.onSuccess(response)
                },
                Response.ErrorListener { error ->
                    Logger.d(TAG, error.toString())
                    volleyHandler.onError(error.toString())
                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    return headers()
                }

                override fun getBody(): ByteArray {
                    return JSONObject(body as Map<*, *>).toString().toByteArray()
                }
            }
            MyApplication.instance!!.addToRequestQueue(stringRequest)
        }

        fun del(url: String, volleyHandler: VolleyHandler) {
            val stringRequest = object : StringRequest(Method.DELETE, server(url),
                Response.Listener { response ->
                    Logger.d(TAG, response)
                    volleyHandler.onSuccess(response)
                },
                Response.ErrorListener { error ->
                    Logger.d(TAG, error.toString())
                    volleyHandler.onError(error.toString())
                }) {
            }
            MyApplication.instance!!.addToRequestQueue(stringRequest)
        }

        /**
         *  Request Api`s
         */

        var API_LIST_POST = "employees"
        var API_SINGLE_POST = "employee/" //id
        var API_CREATE_POST = "create"
        var API_UPDATE_POST = "update/" //id
        var API_DELETE_POST = "delete/" //id

        /**
         *  Request Param`s
         */

        fun paramsEmpty(): HashMap<String, String> {
            return HashMap<String, String>()
        }

        fun paramsCreate(poster: Poster): HashMap<String, Any> {
            val params = HashMap<String, Any>()
            params["employee_name"] = poster.employee_name
            params["employee_salary"] = poster.employee_salary
            params["employee_age"] = poster.employee_age
            params["profile_image"] = poster.profile_image
            return params
        }

        fun paramsUpdate(poster: Poster): HashMap<String, Any> {
            val params = HashMap<String, Any>()
            params["id"] = poster.id
            params["employee_name"] = poster.employee_name
            params["employee_salary"] = poster.employee_salary
            params["employee_age"] = poster.employee_age
            params["profile_image"] = poster.profile_image
            return params
        }

        /**
         *  Response Parse`s
         */

    }
}