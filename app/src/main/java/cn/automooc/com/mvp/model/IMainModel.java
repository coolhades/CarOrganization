package cn.automooc.com.mvp.model;

import com.hades.libam.ui.interf.IRootModel;

import cn.automooc.com.mvp.interf.IDataCacheCallBack;
import cn.automooc.com.mvp.interf.IDataCallBack;

/**
 * Created by Hades on 16/10/17.
 */

public interface IMainModel extends IRootModel {
    void onLoadData(IDataCallBack callBack);
    void onCacheData(IDataCacheCallBack cacheCallBack);
}
