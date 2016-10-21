package cn.automooc.com.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hades on 16/10/9.
 */

public class HttpRequestUtils {

    private RequestQueue mQueue;
    private static HttpRequestUtils mVollyRequest;
    private static HashMap<String, String> headers;
    public static int REQUEST_GET = 0;
    public static int REQUEST_POST = 1;

    private HttpRequestUtils(Context context) {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(context);
        }
        headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json;charset=utf-8");
        headers.put("os", "Android");
    }

    public static HttpRequestUtils getInstance(Context context) {
        if (mVollyRequest == null) {
            mVollyRequest = new HttpRequestUtils(context);

        }
        return mVollyRequest;
    }

    /**
     * stringRequest requestMethod GET Default
     *
     * @param url
     */

    public void stringRequest(String url, Response.Listener<String> listener,
                              Response.ErrorListener errorListener) {
        StringRequest request = new
                StringRequest(url, listener, errorListener) {

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {

                        return headers;
                    }
                };

        mQueue.add(request);
    }

    /**
     * stringRequest
     *
     * @param requestMethod
     * @param url
     */

    public void stringRequest(int requestMethod, String url, Listener<String>
            listener, ErrorListener errorListener, final Map<String, String> map) {
        if
                (requestMethod == REQUEST_GET) {
            stringRequest(url, listener, errorListener);
        } else if (requestMethod ==
                REQUEST_POST) {
            StringRequest request = new StringRequest(Request.Method.POST,
                    url, listener, errorListener) {

                @Override
                protected Map<String, String> getParams() throws
                        AuthFailureError {
                    return map;
                }

//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//
//                    return headers;
//                }
            };
            mQueue.add(request);
        }
    }

    /**
     * jsonObjectRequest
     * <p>
     * Creates a new request.
     *
     * @param method        the HTTP method to use
     * @param url           URL to fetch the JSON from
     * @param requestBody   A {@link String} to post with the request. Null is allowed and
     *                      indicates no parameters will be posted along with request.
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
//    public void jsonObjectRequest(int method, String url, String requestBody,
//                                  Listener<JSONObject> listener, ErrorListener errorListener) {
//        JsonObjectRequest request = new JsonObjectRequest(method, url,
//                requestBody, listener, errorListener) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//
//                return headers;
//            }
//        };
//        mQueue.add(request);
//    }

    /**
     * jsonObjectRequest
     * <p>
     * Creates a new request.
     *
     * @param url           URL to fetch the JSON from
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
//    public void jsonObjectRequest(String url, Listener<JSONObject> listener,
//                                  ErrorListener errorListener) {
//        jsonObjectRequest(Method.GET, url, null, listener, errorListener);
//    }

    /**
     * jsonObjectRequest
     * <p>
     * Creates a new request.
     *
     * @param method        the HTTP method to use
     * @param url           URL to fetch the JSON from
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
//    public void jsonObjectRequest(int method, String url,
//                                  Listener<JSONObject> listener, ErrorListener errorListener) {
//        jsonObjectRequest(method, url, null, listener, errorListener);
//    }

    /**
     * jsonArrayRequest
     * <p>
     * Creates a new request.
     *
     * @param method        the HTTP method to use
     * @param url           URL to fetch the JSON from
     * @param requestBody   A {@link String} to post with the request. Null is allowed and
     *                      indicates no parameters will be posted along with request.
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */

//    public void jsonArrayRequest(int method, String url, String requestBody,
//                                 Listener<JSONArray> listener, ErrorListener errorListener) {
//        JsonArrayRequest request = new JsonArrayRequest(method, url, requestBody,
//                listener, errorListener) {
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//
//                return headers;
//            }
//        };
//        mQueue.add(request);
//    }

    /**
     * jsonArrayRequest
     * <p>
     * Creates a new request.
     *
     * @param url           URL to fetch the JSON from
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */

//    public void jsonArrayRequest(String url, Listener<JSONArray> listener,
//                                 ErrorListener errorListener) {
//        jsonArrayRequest(Method.GET, url, null,
//                listener, errorListener);
//    }

    /**
     * jsonArrayRequest
     * <p>
     * Creates a new request.
     *
     * @param method        the HTTP method to use
     * @param url           URL to fetch the JSON from
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */

//    public void jsonArrayRequest(int method, String url, Listener<JSONArray>
//            listener, ErrorListener errorListener) {
//        jsonArrayRequest(Method.GET,
//                url, null, listener, errorListener);
//    }

}

