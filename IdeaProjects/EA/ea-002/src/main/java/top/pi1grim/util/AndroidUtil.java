package top.pi1grim.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.aspectj.weaver.ast.Or;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import top.pi1grim.bean.OriginData;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AndroidUtil {
    private static AppiumDriver driver = null;

    private AndroidUtil() {

    }

    public static File getQRFile(){
        DesiredCapabilities des = new DesiredCapabilities();
        des.setCapability("platformName", "Android");
        des.setCapability("deviceName", "V1916A");
        try {
            driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), des);
            MyTimer.sleep(5);
        }catch (Exception e){
            System.out.println("登陆失败");
        }
        File sourceFile = null;
        try {
            driver.findElementByXPath("//*[@text=\"平板和手机同时登录\"]").click();
            MyTimer.sleep(3);
            //step2
            sourceFile = driver.getScreenshotAs(OutputType.FILE);//截图成功
            BufferedImage image = ImageIO.read(sourceFile);
            WebElement element = driver.findElementById("com.tencent.mm:id/erm");
            Point p = element.getLocation();
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();
            int left = p.getX(); //指定矩形区域左上角的X坐标
            int right = p.getY(); //指定矩形区域左上角的Y坐标
            int top = left + width;
            int bottom = right + height;
            BufferedImage img = image.getSubimage(left, right, width, height);
            ImageIO.write(img, "png", sourceFile);
//            String filePath = "QR.png";
//            FileUtils.copyFile(sourcefile, new File(filePath));
        }catch(Exception e){
            System.out.println("getQR失败");
            e.printStackTrace();
        }
        System.out.println("二维码爬取成功!");
        return sourceFile ;
    }

    public static boolean isLogin(){
        try {
            driver.findElementByXPath("//*[@text=\"发现\"]");
            System.out.println("登陆成功!");
            return true;
        }catch(Exception e){
            System.out.println("未登录...");
            return false;
        }
    }

    public static List<OriginData> getData(int length){
        List<String> nicknames = new ArrayList<>();
        List<String> contents = new ArrayList<>();
        List<OriginData> data = new ArrayList<>();
        try{
            driver.findElementByXPath("//*[@text=\"发现\"]").click();
            System.out.println("第一步:进入发现,完成");
            Thread.sleep(2000);
            driver.findElementByXPath("//*[@text=\"朋友圈\"]").click();
            System.out.println("第二步:进入朋友圈,完成");
            System.out.println("正在爬取朋友圈数据");
            Thread.sleep(5000);
            while(nicknames.size() <= length + 1){
                WebDriverWait items = new WebDriverWait(driver,10);
                List<WebElement> elements = items.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.tencent.mm:id/hyf")));
                for(WebElement element:elements){
                    try {
                        String nickname = element.findElement(By.id("com.tencent.mm:id/fzg")).getText();
                        String content = element.findElement(By.id("com.tencent.mm:id/bmy")).getText();
                        if (nicknames.contains(nickname) && contents.contains(content)) {
                            continue;
                        } else {
                            System.out.println(nickname + ':' + content);
                            nicknames.add(nickname);
                            contents.add(content);
                        }
                    }catch(Exception e){
                        continue;
                    }
                }
                TouchAction action = new TouchAction(driver);
                action.press(PointOption.point(300, 1000))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                        .moveTo(PointOption.point(300, 300))
                        .release()
                        .perform();
            }
            int size = nicknames.size();
            OriginData originData;
            for (int i = 0; i < size; i++) {
                originData = new OriginData(nicknames.get(i), contents.get(i));
                data.add(originData);
                System.out.println(originData);
            }
        }catch(Exception e){
            System.out.println("getData失败");
            e.printStackTrace();
        }
        return data;
    }
}

