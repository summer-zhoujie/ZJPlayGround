package com.example.playground.idiomdatachange;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class IdiomDataChangeShell {


    private static final String JSON_DIR_PATH = "/Users/summer/Downloads/idiom/tiku_dir";

    public static void main(String[] args) throws Exception {
        File dir = new File(JSON_DIR_PATH);
        if (!dir.exists()) {
            printIn("error: " + dir.getAbsolutePath() + " not exists");
            return;
        }

        for (File file : dir.listFiles()) {
            String fileContent = readFile2String(file);
            final JSONObject jsonObject = new JSONObject(fileContent);
            final JSONObject json = jsonObject.getJSONObject("json");
            final Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject jsonObject_1 = json.getJSONObject(key);
                JSONArray jsonArray_idiom = jsonObject_1.getJSONArray("idiom");
                for (int i = 0; i < jsonArray_idiom.length(); i++) {
                    printIn((String) jsonArray_idiom.get(i));
                }

            }
            break;
        }
    }

    private static String readFile2String(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String line = bufferedReader.readLine();
        while (line != null) {
            stringBuffer.append(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return stringBuffer.toString();
    }

    /**
     * 打印控制台信息
     */
    private static void printIn(String string) {
        System.out.println(string);
    }

    private static void printIn(String tag, String string) {
        System.out.println("[=" + tag + "=] " + string);
    }
}
