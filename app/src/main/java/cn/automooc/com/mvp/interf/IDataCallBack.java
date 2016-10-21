package cn.automooc.com.mvp.interf;

/**
 * Created by Hades on 16/10/17.
 */

public interface IDataCallBack<T> {
    void onSuccess(T data);
    void onFailed(String s);

}
