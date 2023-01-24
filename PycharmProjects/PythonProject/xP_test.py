from lxml import etree
import requests

url = "https://beijing.zbj.com/search/service?kw=%E5%95%86%E6%A0%87%E8%AE%BE%E8%AE%A1&r=1"

res = requests.get(url)
# print(res.text)


html = etree.HTML(res.text)
divs = html.xpath("//*[@id='__layout']/div/div[3]/div/div[3]/div[4]/div[1]/div")

for div in divs:
    print(div.xpath("./div/a/div/div/div/text()"))
    print(div.xpath("./div/a/div/div/span/text()"))
    print(div.xpath("./div/div/div/div/span/text()"))
    print(div.xpath("./div/div/div/span/text()"))
    print("\n")
