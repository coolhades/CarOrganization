package cn.automooc.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import cn.automooc.com.R;
import cn.automooc.com.adapter.MyClassListViewAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.MyClass;
import cn.automooc.com.bean.ResultMyClass;
import cn.automooc.com.utils.ConstantSet;

public class MyClassActivity extends AppCompatActivity {


    ListView listView;
    List<MyClass> lists;
    MyClassListViewAdapter adapter;
    
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class);

        initView();
        initData();
        initEvent();
    }

    private void initView() {

        listView = (ListView) findViewById(R.id.my_class_listview);
        back= (ImageView) findViewById(R.id.back);
    }


    private void initData() {

        lists = new ArrayList<MyClass>();
        getData();
    }

    private void initEvent() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                MyClassActivity.this.finish();
            }
        });
        
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ConstantSet.videoTitle=lists.get(i).getCourse_name();
                ConstantSet.course_id=lists.get(i).getImpower_id();
                try {
                    //此处跳转 逻辑待确认
                    Intent im = new Intent(MyClassActivity.this, VideoActivity.class);
                    im.putExtra("class_id", lists.get(i).getClass_id());
                    startActivity(im);
                }catch (Exception e){

                }
            }
        });
    }


    public void getData() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "user/getmycourse?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                System.out.print("response  " + response + "    " + response.length());
                Log.i("TAG-MyCourse", response);
                try {


                        Gson gson = new Gson();
                        ResultMyClass resultUser = gson.fromJson(response, new TypeToken<ResultMyClass>() {
                        }.getType());

                        if (resultUser.getStatus().equals("1")) {

                            lists = resultUser.getData();
                            adapter = new MyClassListViewAdapter(lists, MyClassActivity.this);
                            listView.setAdapter(adapter);
                        }

                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(MyClassActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("user_id", ConstantSet.user.getUid());
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }

}
