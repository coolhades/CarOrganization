package cn.automooc.com.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hades.libam.net.RootRequest;

import java.util.HashMap;
import java.util.Map;

import cn.automooc.com.api.LoginApi;
import cn.automooc.com.application.MyApplication;
import cn.automooc.com.bean.ResultUser;
import cn.automooc.com.bean.User;
import cn.automooc.com.model.ISaveUserData;
import cn.automooc.com.model.SaveUserDataModel;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.Md5Utils;
import cn.automooc.com.views.viewinterface.IOnLogin;

/**
 * Created by Hades on 16/9/29.
 */

public class LoginPresenter implements ILoginPresenter {
    private ISaveUserData iSaveUserData;
    private IOnLogin IOnLogin;
    private Context mContext;


    private ILoadData loadData;

    public void setLoadDataListener(ILoadData ll){
        loadData = ll;
    }


    public LoginPresenter(Context context, IOnLogin loginInterface) {
        this.IOnLogin = loginInterface;
        this.mContext = context;
        iSaveUserData = new SaveUserDataModel(context);
    }

    @Override
    public void Login(String account, String passwd) {
        loginUser(account, passwd);
    }

    @Override
    public void Register(String account, String passwd) {
        resigterUser(account, passwd);
    }


    //  登录
    private void loginUser(final String account, final String passwd) {

        new LoginApi().login(account, passwd, new RootRequest.ApiCallback() {
            @Override
            public void onSuccess(Object ret) {
                loadData.onLoadData(ret);

                Gson gson = new Gson();
                try {
                    User resultUser = (User) ret;
                    Log.d("TAG-Login", gson.toJson(resultUser));
                    iSaveUserData.saveData(resultUser);
                    IOnLogin.OnLoginSuccess(resultUser.getNickname());

                } catch (Exception e) {

                }
            }

            @Override
            public void onError(String err_msg) {
            }

            @Override
            public void onFailure() {
                Log.d("TAG-Login", "网络请求失败");
            }
        });


//        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "user/login?", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                // TODO Auto-generated method stub
//                    Gson gson = new Gson();
//                try {
//
//                    ResultUser resultUser = gson.fromJson(response, new TypeToken<ResultUser>() {
//                    }.getType());
//
//
//
//                    if (resultUser.getStatus().equals("1")) {
//                        Log.d("TAG-Login", gson.toJson(resultUser.getData()));
//                        iSaveUserData.saveData(resultUser);//保存数据到model层
//                        IOnLogin.OnLoginSuccess(resultUser.getData().getNickname());
//                    }
//                }catch (Exception e){
//
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // TODO Auto-generated method stub
//                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                // TODO Auto-generated method stub
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("account", account);
//                map.put("passwd", passwd);
////                map.put("okey", Md5Utils.md5("moocuserlogin" + account + passwd));
//
//                return map;
//            }
//        };
//
//        MyApplication.getRq().add(rq);
    }

    //注册
    private void resigterUser(final String account, final String passwd) {
        StringRequest rq = new StringRequest(Request.Method.POST, ConstantSet.homeAddress + "user/register?", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // TODO Auto-generated method stub
                Log.d("TAG-Register", response);
                try {
                    Gson gson = new Gson();
                    ResultUser resultUser = gson.fromJson(response, new TypeToken<ResultUser>() {
                    }.getType());

                    if (resultUser.getStatus().equalsIgnoreCase("1")) {
                        IOnLogin.OnResigterSuccess("注册成功");
                    } else {
                        IOnLogin.OnLoginFailed("此账户已存在");
//                    showShortToast("此账户已存在");
                    }

                } catch (Exception e) {

                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                Map<String, String> map = new HashMap<String, String>();
                map.put("account", account);
                map.put("passwd", passwd);
                map.put("okey", Md5Utils.md5("moocuserregister" + account + passwd));

                return map;
            }
        };

        MyApplication.getRq().add(rq);
    }
}
