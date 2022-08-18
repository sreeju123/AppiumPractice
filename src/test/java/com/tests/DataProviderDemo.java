package com.tests;

import lombok.Data;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sreej
 */
public class DataProviderDemo {

@Test(dataProvider = "getData")
public void dataProvideTest(Map<String,String> map){
    System.out.println(map.get("username"));
    System.out.println(map.get("password"));
    System.out.println(map.get("location"));

}

@DataProvider
public Object[] getData(){

    Map<String,String> map=new HashMap<>();
    map.put("username","sree");
    map.put("password","123");
    map.put("location","chennai");

    Map<String,String> map1=new HashMap<>();
    map1.put("username","Arun");
    map1.put("password","234123");


    List<Map<String,String>> list=new ArrayList<>();
    list.add(map);
    list.add(map1);
    return list.toArray();
}

//@DataProvider
//public Object[][] getData1(){ // first dimension is number of times
//    return new Object[][]{
//            {"sree","1230"},
//            {"Arun","0987"},
//            {"Jose","1187"}
//    };
//}

}
