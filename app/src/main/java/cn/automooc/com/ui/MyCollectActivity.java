package cn.automooc.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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

import cn.automooc.com.BaseActivity;
import cn.automooc.com.R;
import cn.automooc.com.adapter.MyCollectListViewAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.MyCollect;
import cn.automooc.com.bean.ResultMycollect;
import cn.automooc.com.utils.ConstantSet;

public class MyCollectActivity extends BaseActivity {

    
    ListView myCollectlistView;
    
    //公用Type

    MyCollectListViewAdapter adapter;
    List<MyCollect> lists;
    
    ImageView back;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {
        
        myCollectlistView= (ListView) findViewById(R.id.my_collect_listview);
        
        back= (ImageView) findViewById(R.id.back);
    }

    @Override
    protected void initData() {

        lists=new ArrayList<MyCollect>();

        getData();

       // adapter=new TypeListAdapter(lists,this);

        //myCollectlistView.setAdapter(adapter);
        
    }

    @Override
    protected void initEvent() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                MyCollectActivity.this.finish();
            }
        });
        
        
        myCollectlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ConstantSet.videoTitle=lists.get(i).getCourse_name();
                ConstantSet.course_id=lists.get(i).getImpower_id();

                startActivity(new Intent(MyCollectActivity.this,CourseActivity.class));
            }
        });
    }


    public void getData()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"user/getmyfollowcourse?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                Log.i("TAG-MyCollesction", response);
              //  showShortToast(response);
                if(response.length()>40) {

                    Gson gson = new Gson();
                    try {
                        ResultMycollect resultUser = gson.fromJson(response, new TypeToken<ResultMycollect>() {
                        }.getType());


                        lists = resultUser.getData();
                        adapter = new MyCollectListViewAdapter(lists, MyCollectActivity.this);

                        myCollectlistView.setAdapter(adapter);
                    }catch (Exception e){

                    }
                }

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(MyCollectActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
}
