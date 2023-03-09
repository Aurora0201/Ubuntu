from appium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
import time
import pandas as pd

PLATFORM = 'Android'
deviceName = 'V1916A'
app_package = 'com.tencent.mm'
app_activity = '.ui.LauncherUI'
driver_server = 'http://127.0.0.1:4723/wd/hub'

class Moments(object):
    def __init__(self):
        self.desired_caps = {
            'platformName': PLATFORM,
            'deviceName': deviceName,
            # 'appPackage': app_package,
            # 'appActivity': app_activity
        }
        self.driver = webdriver.Remote(driver_server, self.desired_caps)
        self.wait = WebDriverWait(self.driver, 300)

    def login(self):
        #el0 = self.driver.find_element_by_xpath('//android.widget.TextView[@content-desc="微信"]')
        #el0.click()
        #time.sleep(2)
        
        el1 = self.driver.find_element_by_xpath('//*[@text="平板和手机同时登录"]')
        el1.click()
        time.sleep(3)
        #获取微信二维码
        img_path = '/home/binjunkai/repo/IdeaProjects/EmotionAnalysis-VM/src/main/webapp/static/QR.png'
        self.driver.save_screenshot(img_path)
        QR = self.driver.find_element_by_id('com.tencent.mm:id/erm')
        left = QR.location['x']  # 区块截图左上角在网页中的x坐标
        top = QR.location['y']  # 区块截图左上角在网页中的y坐标
        right = left + QR.size['width']  # 区块截图右下角在网页中的x坐标
        bottom = top + QR.size['height']
        from PIL import Image
        picture = Image.open(img_path)
        picture = picture.crop((left, top, right, bottom))  # 二次截图：形成区块截图
        picture.save(img_path)
        print("--已下载完成二维码")
        img=Image.open(img_path)
        img.show()

        time.sleep(20)
        #####

        ######
        time.sleep(2)
        el2 = self.driver.find_element_by_xpath('//*[@text="发现"]')
        el2.click()    
        time.sleep(2)
        el3 = self.driver.find_element_by_xpath(
            '//*[@text="朋友圈"]'
            )
        el3.click()
        time.sleep(2)
        #开始抓取数据
        nicknames = ['']
        contents = ['']
        while len(nicknames)<=5:
            items = self.wait.until(EC.presence_of_all_elements_located(
                (By.ID, 'com.tencent.mm:id/hyf')))
            for item in items:
                try:
                    nickname = item.find_element_by_id('com.tencent.mm:id/fzg').get_attribute('text')
                    content = item.find_element_by_id('com.tencent.mm:id/bmy').get_attribute('text')
                    #content = item.find_element_by_class_name("android.widget.LinearLayout").get_attribute('text')
                    if nickname in nicknames and content in contents:
                        continue
                    else:
                        print("姓名:"+nickname+" 评论:"+content)
                        nicknames.append(nickname)
                        contents.append(content)
                    #self.collection.update({'nickname': nickname, 'content': content}, {'$set': data}, True)
                except BaseException as e:
                    continue
            self.driver.swipe(300, 1000, 300, 300)
        data = {'nickname':nicknames[1:],'content':contents[1:]}
        df = pd.DataFrame(data)
        return df
    def init(self):
        el0 = self.driver.find_element_by_xpath('//android.widget.ImageView[@content-desc="返回"]')
        el0.click()
        time.sleep(2)
        el1 = self.driver.find_element_by_xpath('//*[@text="我"]')
        el1.click()
        time.sleep(2)
        el2 = self.driver.find_element_by_xpath('//*[@text="设置"]')
        el2.click()
        time.sleep(2)
        el3 = self.driver.find_element_by_xpath('//*[@text="设置"]')
        el3.click()
        time.sleep(2)
        el4 = self.driver.find_element_by_xpath('//*[@text="退出"]')
        el4.click()
        time.sleep(2)
        el5 = self.driver.find_element_by_id('com.tencent.mm:id/ipm')
        el5.click()
        time.sleep(5)
        el6 = self.driver.find_element_by_xpath('//*[@text="退出"]')
        el6.click()
        time.sleep(2)
        el7 = self.driver.find_element_by_xpath('//*[@text="确定"]')
        el7.click()
        time.sleep(5)
M = Moments()
df = M.login()
M.init()
print(df)

