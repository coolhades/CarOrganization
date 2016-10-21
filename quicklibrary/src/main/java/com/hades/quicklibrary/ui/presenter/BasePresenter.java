package com.hades.quicklibrary.ui.presenter;

import android.util.Log;

import com.hades.quicklibrary.logtag.DebugTAG;
import com.hades.quicklibrary.ui.interf.IRootModel;
import com.hades.quicklibrary.ui.interf.IRootView;

/**
 * Created by Hades on 16/10/8.
 * Presenter基类
 */

public abstract class BasePresenter<T extends IRootView, M extends IRootModel> implements IRootPresenter<T> {

    protected static final String TAG = "BasePresenter";
    protected T mView;
    protected M mModel;

    @Override
    public void attachView(T view) {
        Log.d(DebugTAG.TAG_INFO, "attachView");
        mView = view;

    }

    @Override
    public void detachView() {
        Log.d(DebugTAG.TAG_INFO, "detachView");
        mView = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public T getView() {
        return mView;
    }

    public M getModel() {
        return mModel;
    }
}
