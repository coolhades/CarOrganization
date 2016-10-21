package com.hades.quicklibrary.ui.interf;

/**
 * Created by Hades on 16/10/12.
 * 服务器返回的数据
 */

public interface ILoadData<T> {
    void onLoadData(T t);
}
