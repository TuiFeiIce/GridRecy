package com.yhyy.gridrecy.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IceWolf on 2019/9/13.
 */
public class Contracts {
    public static final List<String> typeList = new ArrayList<String>() {//设置列表
        {
            add(new String("切换布局"));
            add(new String("无规则布局"));
        }
    };
}
