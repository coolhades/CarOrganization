package cn.automooc.com.views.viewinterface;

/**
 * Created by Hades on 1/9/29.
 */

public interface IOnLogin {
    void OnLoginSuccess(String result);
    void OnLoginFailed(String result);
    void OnResigterSuccess(String result);
}
