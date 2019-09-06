package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteFilesImp implements WriteFiles {
    public void writeResultOne(List<String> list){
        String path = ".\\results\\Result1.txt";
        for(String item : list){
            IOUtil.writeLog(path,item);
        }
    }
    public void writeResultTwo(List<String> list){
        String path = ".\\results\\Result2.txt";
        for(String item : list){
            String[] splitStr = item.split(",");
            item = splitStr[0]+",平均薪资（元/月）:"+splitStr[splitStr.length-1];
            IOUtil.writeLog(path,item);
        }
    }
    public void writeResultThree(double salary){
        IOUtil.writeLog(".\\results\\Result3.txt","全国大数据工程师平均薪资："+String.valueOf(salary));
    }
    public void writeResultFour(HashMap<String,HashMap<String, ArrayList<String>>> allSalaryMap, boolean isDevide){
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
                IOUtil.writeLog(path,job+",此工作的平均薪资："+salary);
            }
        }
    }

    public void writeCityAvg(HashMap<String,String> cityAvg){
        for(Map.Entry<String,String> entry : cityAvg.entrySet()){
            IOUtil.writeLog(".\\results\\Result5.txt",entry.getKey()+",此城市的平均薪资："+entry.getValue());
        }
    }

}
