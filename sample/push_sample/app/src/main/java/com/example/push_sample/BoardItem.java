package com.example.push_sample;

import java.io.Serializable;

class BoardItem implements Serializable {
    String title,msg,activity;

    public BoardItem(String id, String title, String activity) {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
