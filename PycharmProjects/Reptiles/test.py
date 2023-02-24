import numpy as np
from lxml import etree
import requests
import time
a = np.array([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]])
b = np.array([1, 2, 3, 4])
# print(a)
# print('\n')
# print(a[-1, -1])
# print('\n')
# print(a[1:, 2:])
# print(b[-2])

for i in range(0, 100):
    if i % 25 == 0:
        time.sleep(1)
        url = 'https://movie.douban.com/top250?start=' + str(i) + '&filter='
        headers = {
            "user-agent": 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/'
                          '537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36'
        }
        res = requests.get(url, headers=headers)
        page_content = res.text
        html = etree.HTML(page_content)
        divs = html.xpath("//*[@id='content']/div/div[1]/ol/li")
        for div in divs:
            print(div.xpath("./div/div/div/a/span[@class='title'][1]/text()"))
            print(div.xpath("./div/div/div/div/span/text()"))

