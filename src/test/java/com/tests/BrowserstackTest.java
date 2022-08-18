package com.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Sreej
 */
public class BrowserstackTest {

    @Test
    public void runTestInBS(Method m) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("browserstack.user", "sreejithr_tbQOle");
        capabilities.setCapability("browserstack.key", "bzmC1Qq9N6swwMSHZzgw");
        // Which app to open
        capabilities.setCapability(MobileCapabilityType.APP, "bs://01d7aa3c94db57c3961971a8b69956893bf4f823");
        capabilities.setCapability("device", "Samsung Galaxy Note 20");
        capabilities.setCapability("os_version", "10.0");
        capabilities.setCapability("project", "First Java Project");
        capabilities.setCapability("build", "Java Android");
        capabilities.setCapability("name", m.getName());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.appium.android.apis");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
//        capabilities.setCapability(MobileCapabilityType.UDID,"9dc0c0a2");

        AndroidDriver<AndroidElement> driver =
                new AndroidDriver<AndroidElement>(new URL("http://hub.browserstack.com/wd/hub"), capabilities);
//        int size = driver.findElements(By.xpath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.TextView")).size();
//        for (int i = 0; i < size; i++) {
//            System.out.println(driver.findElements(By.xpath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.TextView")).get(i).getText());
//        }
        driver.findElements(By.xpath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.TextView"))
                .stream().map(WebElement::getText).forEach(System.out::println);

        driver.quit();
    }
}
