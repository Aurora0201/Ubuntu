from lxml import etree
import requests

url = "https://www.pearvideo.com/video_1682606"
url.split("_")[-1]
source_url = f'https://www.pearvideo.com/videoStatus.jsp?contId={url.split("_")[-1]}&mrd=0.9199176582096389'

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36',
    'Referer': url
}
res = requests.get(source_url, headers=headers)
# print(res.text)
page = res.json()
systemTime = page['systemTime']
# print(page['videoInfo']['videos']['srcUrl'])
process_url = page['videoInfo']['videos']['srcUrl'].replace(systemTime, f'cont-{url.split("_")[-1]}')

html = etree.HTML(requests.get(url).text)
name = html.xpath("//*[@id='detailsbd']/div[1]/div[2]/div/div[1]/h1/text()")[0] + '.mp4'

with open("Video/"+name, mode='wb') as f:
    f.write(requests.get(process_url).content)

print("Over")
