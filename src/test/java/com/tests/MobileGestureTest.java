package com.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.aspectj.weaver.ast.And;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * @author Sreej
 */
public class MobileGestureTest {

    AndroidDriver<AndroidElement> driver;

    @BeforeMethod
    public void setUp(Method m) throws MalformedURLException {
        getDriver(m);
    }

    @AfterMethod
    public void tearDown() throws MalformedURLException {
        driver.quit();
    }

    @Test
    public void mobileGestureTest() {
        AndroidElement views = driver.findElementByAccessibilityId("Views");
        Tap(views);
//        AndroidElement dateWidget = driver.findElementByAccessibilityId("Date Widgets");
//        Tap(dateWidget);

        scrollToSpecificElementandClick(By.xpath("//*[@text='TextFields']"),driver);
//        AndroidElement textField = driver.findElementByAccessibilityId("TextFields");
//        Tap(textField);
    }

    private void Tap(AndroidElement views) {
        new AndroidTouchAction(driver).tap(TapOptions.tapOptions().withElement(ElementOption.element(views))).perform();
    }


    private void getDriver(Method m) throws MalformedURLException {
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
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        driver = new AndroidDriver<AndroidElement>(new URL("http://hub.browserstack.com/wd/hub"), capabilities);

    }

    public void scrollToSpecificElementandClick(By by, AndroidDriver<AndroidElement> driver) {
        while (driver.findElements(by).isEmpty()) {
            Dimension dimension = driver.manage().window().getSize();
            Double screenHeightStart = dimension.getHeight() * 0.5;
            int scrollStart = screenHeightStart.intValue();
            Double screenHeightEnd = dimension.getHeight() * 0.2;
            int scrollEnd = screenHeightEnd.intValue();
            int center = (int) (dimension.width * 0.5);
            new AndroidTouchAction(driver).press(PointOption.point(center, scrollStart)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(center, scrollEnd)).release().perform();
        }
            if (!driver.findElements(by).isEmpty()) {
                driver.findElement(by).click();
            }

        }
    }

