package com.example.playground.drawcolor;

import android.content.Context;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.text.TextUtils;
import android.util.Log;

import com.zj.tools.mylibrary.ZJLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PathParserHelper {

    private static volatile ExecutorService executorService;

    private static ExecutorService getExecutorService() {
        if (executorService == null) {
            synchronized (PathParserHelper.class) {
                if (executorService == null) {
                    executorService = Executors.newSingleThreadExecutor();
                }
            }
        }
        return executorService;
    }

    public interface ToPathArrayListener {
        void onSuccess(ArrayList<Path> paths);

        void onFailed(int code, String msg);
    }

    public static void toPathArray(final Context context, final String assetFilePath, final ToPathArrayListener listener) {
        getExecutorService().submit(new Runnable() {
            @Override
            public void run() {
                final ArrayList<Path> paths = new ArrayList<>();
                String errorMsg = null;
                InputStream open = null;
                try {
                    open = context.getAssets().open(assetFilePath);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(open));
                    ZJLog.d("***************** start read pathdata ");
                    while (true) {
                        String line = reader.readLine();
                        if (!TextUtils.isEmpty(line)) {
                            int z = line.lastIndexOf("z");
                            if (z > 0) {
                                line = line.substring(0, z + 1);
                            }
                            ZJLog.d("path=" + line);
                            paths.add(androidx.core.graphics.PathParser.createPathFromPathData(line));
                        } else {
                            break;
                        }

                    }
                    ZJLog.d("***************** end read pathdata ");
                } catch (Exception e) {
                    errorMsg = e.getMessage();
                    ZJLog.d("parse pathdata error: " + Log.getStackTraceString(e));
                } finally {
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    final String finalErrorMsg = errorMsg;
                    HandlerUtil.postToMain(new Runnable() {
                        @Override
                        public void run() {
                            if (TextUtils.isEmpty(finalErrorMsg) && listener != null) {
                                listener.onSuccess(paths);
                            } else if (!TextUtils.isEmpty(finalErrorMsg) && listener != null) {
                                listener.onFailed(-1, finalErrorMsg);
                            }
                        }
                    });
                }
            }
        });
    }

    public static Path findAInThePath(ArrayList<Path> arrayList, float x, float y) {

        if (arrayList==null||arrayList.isEmpty()) {
            return null;
        }

        for (Path path : arrayList) {
            //创建一个矩形
            RectF rectF = new RectF();
            //获取到当前省份的矩形边界
            path.computeBounds(rectF, true);
            //创建一个区域对象
            Region region = new Region();
            //将path对象放入到Region区域对象中
            region.setPath(path, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
            //返回是否这个区域包含传进来的坐标
            if (region.contains((int) x, (int) y)) {
                return path;
            }
        }
        return null;
    }
}
