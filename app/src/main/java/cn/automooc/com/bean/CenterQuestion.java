package cn.automooc.com.bean;

import java.util.List;

/**
 * Created by jiuzheyange on 2016/8/22.
 */
public class CenterQuestion {
    List<Question> list;
    
    String num;
    String page;
    String pagesize;
    String pagemax;

    public List<Question> getList() {
        return list;
    }

    public void setList(List<Question> list) {
        this.list = list;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getPagemax() {
        return pagemax;
    }

    public void setPagemax(String pagemax) {
        this.pagemax = pagemax;
    }
}
