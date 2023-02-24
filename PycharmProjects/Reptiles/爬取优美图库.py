from lxml import etree
import requests
import time

url = 'https://www.umei.cc/bizhitupian/'
res = requests.get(url)
res.encoding = 'utf-8'

html = etree.HTML(res.text)
child_page = html.xpath("/html/body/main[2]/div[1]/ul/li/a/@href")

head = "https://www.umei.cc"
for child in child_page:
    i = 0
    if i == 10:
        break
    i += 1

    child_url = head + child
    child_res = requests.get(child_url)
    child_res.encoding = 'utf-8'
    child_html = etree.HTML(child_res.text)
    img_url = child_html.xpath("/html/body/main/div[1]/div/section/a/img/@src")
    print(img_url[0])
    img = requests.get(img_url[0])

    img_name = img_url[0].split("/")[-1]

    # with open("img/"+img_name, mode="wb") as f:
    #     f.write(img.content)
    #     print("Over!")

