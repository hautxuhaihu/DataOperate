package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOUtil {
    /*
     * @Author wangchaojie
     * @Description //TODO 写日志
     * @Date 11:23 2019/7/1
     * @Param
     * @return
     **/
    public static void writeLog(String path,String log){
        try {
            FileWriter fileWriter = new FileWriter (path,true);
            log="\r\n"+log;
            char[] chars = log.toCharArray ();
            fileWriter.write (chars,0,chars.length);
            fileWriter.close ();

        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public static List<String> readLines(String path) throws IOException {
        FileReader fileReader = new FileReader (path);
        List<String> list = new ArrayList<> ();
        BufferedReader bufferedReader = new BufferedReader (fileReader);
        String line;
        while ((line = bufferedReader.readLine ()) != null) {
//            System.out.println (line);
            list.add (line);
        }
        //fileReader.close ();
        bufferedReader.close ();
        return list;
    }

    public static List<String> readTest(String path) throws IOException {
        FileReader fileReader = new FileReader (path);
        char[] array = new char[1024];
        List<String> list = new ArrayList<> ();
        while (fileReader.read (array) != -1) {
            String str = new String (array);
            System.out.println (str);
            list.add (str);
        }
        fileReader.close ();
        return list;
    }
}
