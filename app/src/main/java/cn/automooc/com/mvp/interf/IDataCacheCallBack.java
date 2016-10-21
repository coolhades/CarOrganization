package cn.automooc.com.mvp.interf;

/**
 * Created by Hades on 16/10/17.
 * Presenter保存更新数据使用的回调
 */
public interface IDataCacheCallBack<T> {
    void onDataSavedSuccess(T data);
    void onDataSavedFailed(String s);
}
