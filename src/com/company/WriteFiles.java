package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface WriteFiles {
    public void writeResultOne(List<String> list);
    public void writeHandledData(List<String> list);
    public void writeConutryAvg(double salary);
    public void writeAllJobAvg(HashMap<String,HashMap<String, ArrayList<String>>> allSalaryMap, boolean isDevide);
    public void writeCityAvg(HashMap<String,String> cityAvg);

}
