package com.company;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> allInfoList = new ArrayList<>();
        List<String> infoList = new ArrayList<>();
        HashMap<String,HashMap<String, ArrayList<String>>> allSalaryMap = new HashMap<>();
        HashMap<String,String> cityAvg = new HashMap<>();
        try{
            allInfoList = IOUtil.readLines("E:\\salaryall.txt");//从文件中读数据
        }catch (IOException e){
            System.out.println("读文件有误");
        }
        infoList = StringOpertor.CutString(allInfoList);//处理字符串
        Set infoSet = new HashSet(infoList);//去重
        infoList = new ArrayList<>(infoSet);
        try{
            WriteFilesImp writeFilesImp = new WriteFilesImp();
            writeFilesImp.writeResultOne(infoList);//保存题目一答案
            infoList = SalaryOperator.countAvgSalary(infoList);
            writeFilesImp.writeResultTwo(infoList);//保存题目二答案
            double salary = SalaryOperator.getAvg(infoList);
            writeFilesImp.writeResultThree(salary);//保存题目三答案
            allSalaryMap=SalaryOperator.queryPostAvg(infoList);
            writeFilesImp.writeResultFour(allSalaryMap,false);//保存第四题
            cityAvg = SalaryOperator.getCityAvg(allSalaryMap);
            writeFilesImp.writeCityAvg(cityAvg);//保存第五题
            writeFilesImp.writeResultFour(allSalaryMap,true);//复用第四题代码，保存第6题
        }catch (Exception e){
            System.out.println("写文件有误");
        }
    }
}
