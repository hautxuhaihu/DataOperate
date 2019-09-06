package com.company;

import java.lang.reflect.Array;
import java.util.*;

public class SalaryOperator {
    //因为相同职位不同企业工资可能不同，所以定义{"公司名"：{"岗位":[企业1薪资，企业2薪资...]}}
    public static HashMap<String,HashMap<String,ArrayList<String>>> queryPostAvg(List<String> InfoList){
        /**
         * 本函数功能是获取全国各个岗位的平均薪资
         */
        HashMap<String,HashMap<String, ArrayList<String>>> allSalaryMap = new HashMap<>();
        for(int i=0;i<InfoList.size();i++){
            String infoString = InfoList.get(i);
            String[] splitInfo = infoString.split(",");
            int endIndex = splitInfo.length-1;
            //检查map里面是否已经有此城市信息
            if(allSalaryMap.containsKey(splitInfo[endIndex-2])){
                //检查map里面有没有岗位的信息,if存在，将薪资加进去，如果没有创建新的岗位
                if (allSalaryMap.get(splitInfo[endIndex-2]).containsKey(splitInfo[0])){
                    allSalaryMap.get(splitInfo[endIndex-2]).get(splitInfo[0]).add(splitInfo[endIndex]);//把工资加到list中，key：岗位名称
                }else {
                    ArrayList<String> salaryList = new ArrayList<>();
                    salaryList.add(splitInfo[endIndex]);
                    allSalaryMap.get(splitInfo[endIndex-2]).put(splitInfo[0],salaryList);
                }
            }else {
                //如果map里面没有这个城市，将城市加进去，还有对应这条信息的岗位名称和薪资。
                ArrayList<String> salaryList = new ArrayList<>();
                salaryList.add(splitInfo[endIndex]);
                HashMap<String,ArrayList<String>> hashMap = new HashMap<>();
                hashMap.put(splitInfo[0],salaryList);
                allSalaryMap.put(splitInfo[endIndex-2],hashMap);
            }
        }
        for (Map.Entry<String,HashMap<String,ArrayList<String>>> entryCity : allSalaryMap.entrySet()){
            //遍历字典
            HashMap<String,ArrayList<String>> JobMap  = entryCity.getValue();
            for (Map.Entry<String,ArrayList<String>> entryJob : JobMap.entrySet()){
                ArrayList<String> salaryList = entryJob.getValue();
                double postAvg = getAvg(salaryList);
                entryJob.getValue().add(String.valueOf(postAvg));//将平均工资直接加到value的最后一个，value是一个list
            }
        }
        return allSalaryMap;
    }

    public static  HashMap<String,HashMap<String,ArrayList<String>>> queryCityAvg(List<String> InfoList){
        HashMap<String, ArrayList<String>> allSalaryMap = new HashMap<>();
        HashMap<String,HashMap<String,ArrayList<String>>> allInfo = new HashMap<>();
        for(int i=0;i<InfoList.size();i++){
            String infoString = InfoList.get(i);
            String[] splitInfo = infoString.split(",");
            int endIndex = splitInfo.length-1;
            //检查map里面是否已经有此岗位信息
            if(allSalaryMap.containsKey(splitInfo[0])){
                allSalaryMap.get(splitInfo[0]).add(splitInfo[endIndex]);//把工资加到list中，key：岗位名称
            }else {
                //如果map里面没有这个岗位，将岗位加进去，还有对应的工资。
                ArrayList<String> salaryList = new ArrayList<>();
                salaryList.add(splitInfo[endIndex]);
                allSalaryMap.put(splitInfo[0],salaryList);
            }
        }
        return allInfo;
    }

    public static HashMap<String,String> getCityAvg(HashMap<String,HashMap<String, ArrayList<String>>> allSalaryMap){
        HashMap<String, String> hashMap = new HashMap<>();
        for (Map.Entry<String,HashMap<String,ArrayList<String>>> entryCity : allSalaryMap.entrySet()){
            //遍历字典
            double i = 0;
            HashMap<String,ArrayList<String>> JobMap  = entryCity.getValue();
            for (Map.Entry<String,ArrayList<String>> entryJob : JobMap.entrySet()){
                int endIndex = entryJob.getValue().size()-1;
                i=i+Double.parseDouble(entryJob.getValue().get(endIndex));
            }
            hashMap.put(entryCity.getKey(),String.valueOf(i));
        }
        return hashMap;
    }

    public static double getAvg(List<String> list){
        int i=0;
        double salary = 0;
        for (String item : list){
            i++;
            String[] splitStr = item.split(",");
            double singleAvgSalary = Double.parseDouble(splitStr[splitStr.length-1]);
            salary = salary+singleAvgSalary;
        }
        salary = salary/i;
        return salary;
    }

    public static List<String> countAvgSalary(List<String> infoList){
        String salary="";
        String[] numStr;
        for(int i=0;i<infoList.size();i++) {
            String infoString = infoList.get(i);
            String[] splitInfo = infoString.split(",");
            salary = splitInfo[3];
            if (salary.contains("千") || salary.contains("K") || salary.contains("+")
                    || salary.contains("以") || salary.contains("天") || salary.contains("k")) {
                infoList.remove(i);
                i--;
                continue;
            }
            else{
                if(salary.contains("万/月")){
                    salary = salary.replace("万/月","");
                    if(salary.contains("-")){
                        numStr = salary.split("-");
                    }else {
                        numStr = salary.split("至");
                    }
                    if(numStr.length==2){
                        double numBegin = Double.parseDouble(numStr[0]);
                        double numAfter = Double.parseDouble(numStr[1]);
                        double numAvg = 0.5*(numBegin+numAfter)*1000;
                        salary = String.valueOf(numAvg);
                    }else if (numStr.length==1){
                        salary = String.valueOf(Double.parseDouble(numStr[0])*1000);
                    }else {
                        infoList.remove(i);
                        i--;
                        continue;
                    }
                }else{
                    salary = salary.replace("元","");
                    salary = salary.replace("万","");
                    salary = salary.replace("/","");
                    salary = salary.replace("年","");
                    salary = salary.replace("月","");
                    if(salary.contains("-")){
                        numStr = salary.split("-");
                    }else {
                        numStr = salary.split("至");
                    }
                    if(numStr.length==2){
                        double numBegin = Double.parseDouble(numStr[0]);
                        double numAfter = Double.parseDouble(numStr[1]);
                        double numAvg = 0.5*(numBegin+numAfter);
                        salary = String.valueOf(numAvg);
                    }else if (numStr.length==1){
                        salary = numStr[0];
                    }else {
                        infoList.remove(i);
                        i--;
                        continue;
                    }
                    double avgNum = Double.parseDouble(salary);
//                    System.out.println(avgNum);
                    if(avgNum<60){
                        salary = String.valueOf(avgNum*10000/12);
                    }else if(avgNum>70000){
                        salary = String.valueOf(avgNum/12);
                    }
                }
            }
            infoList.set(i,infoString + ","+salary);
        }
        return infoList;
    }
}
