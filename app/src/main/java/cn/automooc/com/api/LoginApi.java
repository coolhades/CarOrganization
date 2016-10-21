package cn.automooc.com.api;


import com.hades.libam.net.RootRequest;
import com.hades.libam.pojo.BaseRetData;

import cn.automooc.com.bean.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Hades on 16/10/9.
 */

public class LoginApi extends RootRequest {


    private ApiStore mApiStore;

    public LoginApi() {
        super();
        mApiStore = mRetrofit.create(ApiStore.class);
    }

    public void login(String account, String passwd,ApiCallback callback) {
        //返回的response
        Call<BaseRetData<User>> call = ((ApiStore) mApiStore).login(account, passwd);
        call.enqueue(new RetrofitCallback< BaseRetData<User> >(callback));

    }

    //登录接口
    public interface ApiStore {
        @FormUrlEncoded
        @POST("user/login")
        Call<BaseRetData<User>> login(@Field("account") String account,
                                 @Field("passwd") String passwd
        );
    }

}
