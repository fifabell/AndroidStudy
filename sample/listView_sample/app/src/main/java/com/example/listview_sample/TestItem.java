package com.example.listview_sample;

import android.util.Log;

public class TestItem {
    private String itemName;


    public TestItem(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
