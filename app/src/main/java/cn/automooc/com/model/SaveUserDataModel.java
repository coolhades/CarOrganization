package cn.automooc.com.model;

import android.content.Context;

import cn.automooc.com.bean.User;
import cn.automooc.com.utils.ConstantSet;
import cn.automooc.com.utils.SaveUser;

/**
 * Created by Hades on 16/9/29.
 */

public class SaveUserDataModel implements ISaveUserData {

    Context mContext;

    public SaveUserDataModel(Context context) {
        this.mContext = context;
    }

    @Override
    public void saveData(User user) {
        SaveUser save=new SaveUser(mContext);
        ConstantSet.user = user;
        save.saveData("userFile","user",user);
    }


}
