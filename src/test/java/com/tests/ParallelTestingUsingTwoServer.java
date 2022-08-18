package com.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Sreej
 */
public class ParallelTestingUsingTwoServer {

    ExtentReports extentReports;
    @BeforeSuite
    public void setUp(){
        extentReports=new ExtentReports();
        ExtentSparkReporter spark=new ExtentSparkReporter(System.getProperty("user.dir")+"/index.html");
        extentReports.attachReporter(spark);
    }

    @AfterSuite
    public void teatDown(){
        extentReports.flush();
    }

    @Test
    public void myFirstAppiumTest() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // Which app to open
//        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/ApiDemos-debug.apk");


        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"io.appium.android.apis");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,".ApiDemos");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel Emulator 3");
        capabilities.setCapability(MobileCapabilityType.UDID,"emulator-5554");
        // below one is for running parallel test using same server- we need to mention 8200-8201
        capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT,"8200");
        capabilities.setCapability("skipDeviceInstallation",true);
        capabilities.setCapability("skipServerInstallation",true);

        ExtentTest test = extentReports.createTest("My First AppiumTest");
        AndroidDriver<AndroidElement> driver =
                new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.findElementByAccessibilityId("Text").click();
        test.pass("Click Text");
        String screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
        test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

    }


    @Test
    public static void mySecondAppiumTest() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // Which app to open
//        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/ApiDemos-debug.apk");


        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"io.appium.android.apis");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,".ApiDemos");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel Emulator 4");
        capabilities.setCapability(MobileCapabilityType.UDID,"emulator-5556");

        // below one is for running parallel test using same server- we need to mention 8200-8201
        capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT,"8200");

        AndroidDriver<AndroidElement> driver =
                new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        // Can change th port number to run in multiple server

        driver.findElementByAccessibilityId("Views").click();
    }
}

