package cn.automooc.com.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import cn.automooc.com.adapter.MyQuestionListViewAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.MyQuestion;
import cn.automooc.com.bean.Question;
import cn.automooc.com.utils.ConstantSet;

public class MyQuestionActivity extends BaseActivity {

    
    ListView myQuestionListView;
    MyQuestionListViewAdapter adapter;
    List<Question> lists;
    
    ImageView back;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_question);
        
        initView();
        initData();
        initEvent();
    }

    @Override
    protected void initView() {

        myQuestionListView= (ListView) findViewById(R.id.my_question_listview);
        back= (ImageView) findViewById(R.id.back);
    }

    @Override
    protected void initData() {
        
        lists=new ArrayList<Question>();
        getData();
        
    }

    @Override
    protected void initEvent() {


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                MyQuestionActivity.this.finish();
            }
        });
    }



    public void getData()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"user/getmyquestions?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

//                System.out.print("response  "+response+"    "+response.length());
                Log.i("MyQuestion", response);
               // showShortToast(response);
                try {

                    Gson gson = new Gson();
                    MyQuestion resultUser = gson.fromJson(response, new TypeToken< MyQuestion>() {
                    }.getType());
                    if (resultUser.getStatus().equals("1")) {
                        adapter=new MyQuestionListViewAdapter(resultUser.getData().getList(),MyQuestionActivity.this);
                        myQuestionListView.setAdapter(adapter);
                    }

                }catch (Exception e){

                }

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(MyQuestionActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
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
