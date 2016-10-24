package cn.automooc.com.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.automooc.com.R;
import cn.automooc.com.adapter.KechengListAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.LessonBlockKc;
import cn.automooc.com.bean.ResultLessonKc;
import cn.automooc.com.utils.ConstantSet;

/**
 * Created by jiuzheyange on 2016/8/18.
 */
public class KechengView {
    Context mContext;
    View mView;
    ListView listView1;

    List<LessonBlockKc> list1;

    KechengListAdapter adapter1;

    public KechengView(Context mContext) {
        this.mContext = mContext;
    }

    public View getView()
    {
        mView=View.inflate(mContext, R.layout.kecheng_view1,null);
        initView();
        initData();
        initEvent();
        
        
        return mView;
    }

    private void initView() {

        listView1= (ListView) mView.findViewById(R.id.kecheng_listview1);
        listView1.addFooterView(View.inflate(mContext, R.layout.homefragment_footview,null));
    }


    private void initData() {
        list1=new ArrayList<LessonBlockKc>();
        getData("1","10");
    }


    private void initEvent() {
    }



    public void getData(final String page,final String pagesize)
    {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "course/getallcourse?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

//                System.out.print("response  " + response + "    " + response.length());
                Log.i("ID_response", "ID_response="+response);
                try {

                    Gson gson=new Gson();

                    ResultLessonKc resultLessonKc = gson.fromJson(response, new TypeToken<ResultLessonKc>() {
                    }.getType());
                    if (resultLessonKc.getStatus().equalsIgnoreCase("1")) {
                        list1 = resultLessonKc.getData().getList();
                        adapter1 = new KechengListAdapter(list1, mContext);
                        listView1.setAdapter(adapter1);
                    }

                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败1", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("page",page);
                map.put("pagesize",pagesize);
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


}
