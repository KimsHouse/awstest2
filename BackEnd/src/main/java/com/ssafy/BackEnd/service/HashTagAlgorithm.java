package com.ssafy.BackEnd.service;

import java.util.ArrayList;
import java.util.List;

public class HashTagAlgorithm {

    protected static List<String> strList(String content){
        List<String> strlist = new ArrayList<>();
        String[] strArr = content.split(" ");
        for (String s : strArr){
            if(s.charAt(0) == '#') strlist.add(s);
        }

        return strlist;
    }
}
