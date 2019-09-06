package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteFilesImp implements WriteFiles {
    public void writeHandledData(List<String> list,boolean isHasSalary){
        if(isHasSalary){
            String path = ".\\results\\Result2.txt";
            for(String item : list){
                String[] splitStr = item.split(",");
                double salary = new BigDecimal(splitStr[splitStr.length-1]).setScale(2, RoundingMode. HALF_UP ).doubleValue();
                item = splitStr[0]+",平均薪资（元/月）:"+salary;
                IOUtil.writeLog(path,item);
            }
        }else{
            String path = ".\\results\\Result1.txt";
            for(String item : list){
                IOUtil.writeLog(path,item);
            }
        }
    }
    public void writeConutryAvg(double salary){
        salary = new BigDecimal(String.valueOf(salary)).setScale(2, RoundingMode. HALF_UP ).doubleValue();
        IOUtil.writeLog(".\\results\\Result3.txt","全国大数据工程师平均薪资："+salary);
    }
    public void writeAllJobAvg(HashMap<String,HashMap<String, ArrayList<String>>> allSalaryMap, boolean isDevide){
        //二重循环可以复用
        for (Map.Entry<String,HashMap<String,ArrayList<String>>> entryCity : allSalaryMap.entrySet()){
            //遍历字典
            String path = ".\\results\\Result4.txt";
            if(isDevide){
                path = ".\\results\\Result6.txt";
                IOUtil.writeLog(path,"=====================================");
                IOUtil.writeLog(path,entryCity.getKey()+"城市的薪资如下：");
            }
            HashMap<String,ArrayList<String>> JobMap  = entryCity.getValue();
            for (Map.Entry<String,ArrayList<String>> entryJob : JobMap.entrySet()){
                String job = entryJob.getKey();
                String salary = entryJob.getValue().get(entryJob.getValue().size()-1);
                salary = String.valueOf(new BigDecimal(salary).setScale(2, RoundingMode. HALF_UP ).doubleValue());
                IOUtil.writeLog(path,job+",此工作的平均薪资："+salary);
            }
        }
    }

    public void writeCityAvg(HashMap<String,String> cityAvg){
        for(Map.Entry<String,String> entry : cityAvg.entrySet()){
            String salary = String.valueOf(new BigDecimal(entry.getValue()).setScale(2, RoundingMode. HALF_UP ).doubleValue());
            IOUtil.writeLog(".\\results\\Result5.txt",entry.getKey()+",此城市的平均薪资："+salary);
        }
    }

}
