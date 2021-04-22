package com.example.playground.drawcolor;

import android.graphics.Color;
import android.graphics.Path;

class Area {

    public Path path;
    public int status;
    /**
     * 动画过程中的路径
     */
    public Path animPath;

    public static class STATUS {
        /**
         * 默认状态
         */
        public final static int DEFAULT = 1;
        /**
         * 选中: 被选中填成透明格子
         */
        public final static int CHOOSED = 2;
        /**
         * 挖空: 被挖空显露出原图
         */
        public final static int CLIPED = 3;
        /**
         * 挖空动画进行中: 被挖空显露出原图
         */
        public final static int CLIPING = 4;
    }
}
