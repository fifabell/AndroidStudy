package com.example.expandable_sample;

import java.util.ArrayList;

public class MyGroup {
    public ArrayList<String> child;
    public String groupName;
    MyGroup(String name){
        groupName = name;
        child = new ArrayList<String>();
    }

}
