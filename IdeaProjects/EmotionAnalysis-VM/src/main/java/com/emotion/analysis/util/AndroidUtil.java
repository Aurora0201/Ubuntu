package com.emotion.analysis.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;


import javax.imageio.ImageIO;

public class AndroidUtil {
    private static AppiumDriver driver = null;
    static{
        DesiredCapabilities des = new DesiredCapabilities();
        des.setCapability("platformName", "Android");
        des.setCapability("deviceName", "V1916A");
        try {
            driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), des);
            Thread.sleep(5000);
        }catch (Exception e){
            System.out.println("登陆失败");
        }
    }

    public static void main(String[] args) {
        getQR(driver);
    }

    public static File getQR(AppiumDriver driver){
        File sourcefile = null;
        try {
            driver.findElementByXPath("//*[@text=\"平板和手机同时登录\"]").click();
            Thread.sleep(10000);
            //step2
            sourcefile = driver.getScreenshotAs(OutputType.FILE);//截图成功
            BufferedImage image = ImageIO.read(sourcefile);
            WebElement element = driver.findElementById("com.tencent.mm:id/erm");
            Point p = element.getLocation();
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();
            int left = p.getX(); //指定矩形区域左上角的X坐标
            int right = p.getY(); //指定矩形区域左上角的Y坐标
            int top = left + width;
            int bottom = right + height;
            BufferedImage img = image.getSubimage(left, right, width, height);
            ImageIO.write(img, "png", sourcefile);
            String filePath = "QR.png";
            FileUtils.copyFile(sourcefile,new File(filePath));
            System.out.println("二维码爬取成功!");
        }catch(Exception e){
            System.out.println("getQR失败");
            e.printStackTrace();
        }
        return sourcefile;
    }
    public static boolean isLogin(AppiumDriver driver){
        try {
            Thread.sleep(30000);
            driver.findElementByXPath("//*[@text=\"发现\"]");
            System.out.println("登陆成功!");
            return true;
//
//            System.out.println("第一步完成");
//            Thread.sleep(2000);
//            driver.findElementByXPath("//*[@text=\"朋友圈\"]").click();
//            System.out.println("第二步完成");
//            Thread.sleep(5000);
        }catch(Exception e){
            System.out.println("登陆失败");
            e.printStackTrace();
            return false;
        }
    }
    public static void goOn(AppiumDriver driver){
        try{
            driver.findElementByXPath("//*[@text=\"发现\"]").click();
            System.out.println("第一步:进入发现,完成");
            Thread.sleep(2000);
            driver.findElementByXPath("//*[@text=\"朋友圈\"]").click();
            System.out.println("第二步:进入朋友圈,完成");
            System.out.println("正在爬取朋友圈数据");
            Thread.sleep(5000);

        }catch(Exception e){
            System.out.println("getData失败");
            e.printStackTrace();
        }
    }
}
