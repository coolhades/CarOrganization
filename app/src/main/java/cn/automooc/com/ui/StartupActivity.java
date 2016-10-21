package cn.automooc.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hades.libam.cache.ACache;
import com.hades.libam.logtag.ToastTag;
import com.hades.libam.net.RootRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.automooc.com.R;
import cn.automooc.com.api.DomainApi;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.LastResultHome;
import cn.automooc.com.bean.Sign;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.SaveUser;
import cn.automooc.com.utils.ToastUtils;


public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        
        SaveUser save=new SaveUser(this);
        ConstantSet.user=(save.getData("userFile","user"));

//        getMain();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getMain();
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    getData();
//                    getConfi();
//                  //  getConfi();
//                    Thread.sleep(3000);
//                    startActivity(new Intent(StartupActivity.this,LoginAndRegisterActivity.class));
//                    StartupActivity.this.finish();
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        
    }

    //判断是否签到过
    public void getData()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"ding/judgeding?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub

         try{
             Gson gson = new Gson();
             Sign resultUser = gson.fromJson(response, new TypeToken<Sign>() {
             }.getType());
             SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
             String str=formatter.format(new Date());
             if(resultUser.getData()!=null) {
                 ConstantSet.jifen = resultUser.getData().getIntegral_value();
             }
             if(resultUser.getMessage().equals("今天未签到")) {
                 ConstantSet.sign = false;
             }

         }catch (Exception e){

         }

                   

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                if(ConstantSet.user!=null) {
                    map.put("user_uid", ConstantSet.user.getUid());
                }

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }


    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        }catch (ParseException e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }
        return re_time;
    }

    public static Date getFirstdayofThisMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        return cal.getTime();
    }

    //  设置基准域名地址 获取一些配置信息
    public void getMain(){

        new DomainApi(ConstantSet.newhomeAddress2).getDomain("am", new RootRequest.ApiCallback() {
            @Override
            public void onSuccess(Object ret) {
                String data = (String) ret;
                ConstantSet.homeAddress = "http://" + data + "/";
                //设置BaseUrl 才能正常使用
                RootRequest.mBaseUrl = ConstantSet.homeAddress;
                Log.i("TAGTAG", ConstantSet.homeAddress);
                getConfi();
            }

            @Override
            public void onError(String err_msg) {
                ToastUtils.showTextToast(ToastTag.TAG_ERROR, StartupActivity.this);
            }

            @Override
            public void onFailure() {
                ToastUtils.showTextToast(ToastTag.TAG_ERROR, StartupActivity.this);
            }
        });


//        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.newhomeAddress, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                // TODO Auto-generated method stub
//                try {
//                    Gson gson = new Gson();
//                    MainAdressBean mainAdressBean = gson.fromJson(response, MainAdressBean.class);
//                    if (mainAdressBean.getStatus() == 1) {
//                        //获取成功 设置 url前缀
//                        ConstantSet.homeAddress = "http://" + mainAdressBean.getData().trim() + "/";
//                        RootRequest.mBaseUrl = ConstantSet.homeAddress;
//                        Log.i("TAGTAG", ConstantSet.homeAddress);
//                        getConfi();
//
//                    }
//                }catch (Exception e){
//
//                }
//            }
//        }, new Response.ErrorListener(){
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // TODO Auto-generated method stub
//
//            }}){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                // TODO Auto-generated method stub
//                Map<String,String> map=new HashMap<String, String>();
//                map.put("name","am");
//                return map;
//            }
//        };
//
//        MyApplication.getRq().add(rq);
    }

    public void getConfi()
    {
        StringRequest rq=new StringRequest(Request.Method.POST, ConstantSet.homeAddress+"actions/apiconfig?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                try {
                        Gson gson = new Gson();
                        Map<String, String> confiMap = gson.fromJson(response, new TypeToken<Map<String, String>>() {
                        }.getType());
                    if (!confiMap.isEmpty()){
                        ConstantSet.confiMap = confiMap;
                        getHomeData();


                    }else{
                        //失败 跳转登录
                        startActivity(new Intent(StartupActivity.this, ErrorLoginActivity.class));
                        StartupActivity.this.finish();
                }
            }catch (Exception e){

                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }}){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String,String> map=new HashMap<String, String>();
                map.put("timestamp","1471604755");
                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }



    public void getHomeData() {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "main/gethome?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.i("TAGTAG-Home", response);
                Gson gson = new Gson();
                try {
                    LastResultHome lastResultHome = gson.fromJson(response, new TypeToken<LastResultHome>() {
                    }.getType());
                    if (lastResultHome.getStatus().equalsIgnoreCase("1")) {
                        ACache cache = ACache.get(StartupActivity.this);
                        cache.put("HomeData", gson.toJson(lastResultHome.getData()) );
                        startActivity(new Intent(StartupActivity.this, HomeActivity.class));
                        StartupActivity.this.finish();

                    }else{
                        //失败 跳转登录
                        startActivity(new Intent(StartupActivity.this, ErrorLoginActivity.class));
                        StartupActivity.this.finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(StartupActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(StartupActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });

        MyApplication.getRq().add(rq);
    }
    
}
