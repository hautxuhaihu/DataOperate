package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface WriteFiles {
    public void writeResultOne(List<String> list);
    public void writeResultTwo(List<String> list);
    public void writeResultThree(double salary);
    public void writeResultFour(HashMap<String,HashMap<String, ArrayList<String>>> allSalaryMap, boolean isDevide);
    public void writeCityAvg(HashMap<String,String> cityAvg);

}
