import re
import requests
from lxml import etree
url = "http://movie.douban.com/top250"
headers = {
    "user-agent": 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/'
                  '537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36'
}
res = requests.get(url, headers=headers)
page_content = res.text
# obj = re.compile(r'<li>.*?<span class="title">(?P<name>.*?)</span>.*?<p class="">.*?<br>(?P<year>.*?)&nbsp.*?'
#                  r'<span class="rating_num" property="v:average">(?P<score>.*?)</span>.*?'
#                  r'<span>(?P<num>.*?)人评价</span>', re.S)
# result = obj.finditer(page_content)
# for it in result:
#     print(it.group("name"))
#     print(it.group("score"))
#     print(it.group("num")+"人评价")
#     print(it.group("year").strip())

html = etree.HTML(page_content)
divs = html.xpath("//*[@id='content']/div/div[1]/ol/li")
for div in divs:
    print(div.xpath("./div/div/div/a/span[@class='title'][1]/text()"))
    print(div.xpath("./div/div/div/div/span/text()"))

