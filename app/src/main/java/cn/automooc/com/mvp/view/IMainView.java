package cn.automooc.com.mvp.view;

import com.hades.libam.ui.interf.IRootView;

/**
 * Created by Hades on 16/10/17.
 */

public interface IMainView<T> extends IRootView {
    void onLodeData(T data);
}
