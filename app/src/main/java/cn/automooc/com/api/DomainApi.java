package cn.automooc.com.api;


import com.hades.libam.net.RootRequest;
import com.hades.libam.pojo.BaseRetData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Hades on 16/10/10.
 * 获取基准域名
 */

public class DomainApi extends RootRequest {

    private ApiDomain domainApi;


    public DomainApi(String baseUrl) {
        super(baseUrl);
        this.domainApi = mRetrofit.create(ApiDomain.class);
    }

    public void getDomain(String s, ApiCallback callback){
        Call<BaseRetData<String>> call = domainApi.login(s);
        call.enqueue(new RetrofitCallback<BaseRetData<String>>(callback));
    }

    public interface ApiDomain {
        @FormUrlEncoded
        @POST("main/getdomain")
        Call<BaseRetData<String>> login(@Field("name") String s
        );
    }
}
