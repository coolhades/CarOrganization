package cn.automooc.com.mvp.presenter;

import com.hades.libam.ui.presenter.BasePresenter;

import cn.automooc.com.mvp.model.MainModel;
import cn.automooc.com.mvp.view.IMainView;

/**
 * Created by Hades on 16/10/17.
 */

public class MainPresenter extends BasePresenter<IMainView,MainModel> {

    public MainPresenter() {
        setmModel(new MainModel());
    }

    @Override
    public void start() {

    }


    public void getUserInfo() {
    }

    public void getCourseInfo() {
    }
}
