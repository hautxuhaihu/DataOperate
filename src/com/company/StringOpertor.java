package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringOpertor {
    public static List<String> CutString(List<String> allInfoList) {
        for (int i = 0; i < allInfoList.size(); i++) {
            String singleInfo = allInfoList.get(i);
            String[] splitInfo = singleInfo.split(",");
            if (splitInfo.length != 8) {
                allInfoList.remove(singleInfo);//删除不是8列的数据
                i--;//删除一个，list大小减一
            } else {
                //删除第一列和网址列
                List<String> list = Arrays.asList(splitInfo);
                List<String> arrayList = new ArrayList<>(list);
                arrayList.remove(0);
                arrayList.remove(4);
                allInfoList.set(i, String.join(",", arrayList));

                if (splitInfo[3].contains("不限经验") || splitInfo[4].contains("面议")) {
                    allInfoList.remove(singleInfo);
                    i--;//删除一个，list大小减一
                }
            }
        }
        return allInfoList;
    }
}
