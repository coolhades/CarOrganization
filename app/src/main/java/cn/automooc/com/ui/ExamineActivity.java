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
import cn.automooc.com.adapter.MyExamineListViewAdapter;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.MyExam;
import cn.automooc.com.bean.ResultMyExam;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.ShowMaskDialogUtils;


//我的考试页面
public class ExamineActivity extends BaseActivity {

    
    ListView myExamineListView;
    
    List<MyExam> lists;
    
    MyExamineListViewAdapter adapter;
    
    ImageView back;
    ImageView guide_iv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine);
        initView();
        initData();
        initEvent();
        
    }

    @Override
    protected void initView() {

        myExamineListView= (ListView) findViewById(R.id.my_examine_listview);

        back= (ImageView) findViewById(R.id.back);
        guide_iv = (ImageView) findViewById(R.id.guide_iv);

        ShowMaskDialogUtils.showMaskDialog(ExamineActivity.this,"myExamFile","myExam","Guide.Grade.Share");

//        SaveString save=new SaveString(ExamineActivity.this);
//        String videoFirst=save.getPhoneNumber("myExamFile","myExam");
//
//        if(videoFirst==null||videoFirst.equals("")) {
//            MaskDialog dialog1 = new MaskDialog(ExamineActivity.this,"myExamFile","myExam","Guide.Grade.Share");
//
//            Window window1 = dialog1.getWindow();
//            window1.setGravity(Gravity.CENTER);
//            dialog1.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
//            dialog1.show();
//        }
    }

    @Override
    protected void initData() {
        
        lists=new ArrayList<MyExam>();
//        getData();
        String tag = getIntent().getStringExtra("Learn");
        if (!tag.equalsIgnoreCase("Learn")) {
            String courseid = getIntent().getStringExtra("course_id");
            getExamData();//我的 跳转过来
        }else {
            getData(); //获取特定课程
        }
//        getData();
    }

    @Override
    protected void initEvent() {
        


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                ExamineActivity.this.finish();
            }
        });

        guide_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMaskDialogUtils.showMaskDialog_Btn(ExamineActivity.this,"myExamFile","myExam","Guide.Grade.Share");
            }
        });
    }

//获取考试列表
    public void getExamData(){
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"user/getmyexam?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                System.out.print("response  "+response+"    "+response.length());
                Log.i("TAGTAG考试列表", response);
//                Log.i("Course_id", ConstantSet.home_impower_id);
                // showShortToast(response);
                if(response.length()>40) {

                    Gson gson = new Gson();
                    ResultMyExam resultUser = gson.fromJson(response, new TypeToken<ResultMyExam>() {
                    }.getType());


                    lists=resultUser.getData();
                    adapter=new MyExamineListViewAdapter(resultUser.getData(),ExamineActivity.this);

                    myExamineListView.setAdapter(adapter);
                }

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(ExamineActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }

//                map.put("course_id","9CEC3DACD6524CF7B9E35FD94E1FE783");


                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


// 获取某一课程下考试列表
    public void getData()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"user/getmyexam?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

                System.out.print("response  "+response+"    "+response.length());
                Log.i("TAGTAG考试列表", response);
               // showShortToast(response);
                if(response.length()>40) {

                    Gson gson = new Gson();
                    ResultMyExam resultUser = gson.fromJson(response, new TypeToken<ResultMyExam>() {
                    }.getType());


                    lists=resultUser.getData();
                    adapter=new MyExamineListViewAdapter(resultUser.getData(),ExamineActivity.this);

                    myExamineListView.setAdapter(adapter);


//                    GongGaoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//
//                            WebViewKaoshiDialog dialog =  new  WebViewKaoshiDialog(ExamineActivity.this,ConstantSet.homeAddress+lists.get(i).getExam_link(),
//                                    "考试", 0, null,null);
//
//                            Window window = dialog.getWindow();
//                            window.setGravity(Gravity.BOTTOM);
//                            dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
//                            dialog.show();
//                        }
//                    });

//                    if (resultUser.getStatus().equals("1")) {
//
//
//                      
//                    }
                }

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(ExamineActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                if(ConstantSet.user!=null) {
                    map.put("user_id", ConstantSet.user.getUid());
                }
                map.put("class_id", ConstantSet.class_id);
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
}
