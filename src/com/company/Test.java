package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args){
        String str = "15000--25000  薪";
        String[] a = str.split("-");
        System.out.println(a.toString());
//        String[] strings = {"a","b","c","d"};
//        List<String> list=Arrays.asList(strings);
//        List<String> arrayList = new ArrayList<>(list);
//        arrayList.remove(2);
//        String str = String.join(",",arrayList);
//        System.out.println(str);
    }

}
