package com.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.Test;

/**
 * @author Sreej
 */

public class ExtentReportsDemo {

    @Test
    public void extentReportTest(){
        ExtentReports extentReports=new ExtentReports();
        ExtentSparkReporter spark=new ExtentSparkReporter(System.getProperty("user.dir")+"/index.html");
        extentReports.attachReporter(spark);

        ExtentTest test = extentReports.createTest("My First Test").assignAuthor("sree").assignCategory("smoke");
    test.pass("Device connected");
        test.pass("App Loaded");
        test.pass("Done");

        ExtentTest test1 = extentReports.createTest("My Second Test").assignAuthor("sneha").assignCategory("sanity")
                .assignCategory("smoke");
        test1.pass("Device2 connected");
        test1.info("App2 Loaded");
        test1.info("Done2");
        extentReports.flush();
    }

}
