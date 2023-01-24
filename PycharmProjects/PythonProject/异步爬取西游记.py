import asyncio
import requests
import aiohttp
import aiofiles
import json

# https://dushu.baidu.com/api/pc/getCatalog?data={"book_id":"4355370985"}
# https://dushu.baidu.com/api/pc/getChapterContent?data={"book_id":"4355370985","cid":"4355370985|1566855961","need_bookinfo":1}


async def download_content(book_id, c_id, c_name):
    data = {
        "book_id": book_id,
        "cid": f'{book_id}|{c_id}',
        "need_bookinfo": 1
    }
    data = json.dumps(data)
    curl = 'https://dushu.baidu.com/api/pc/getChapterContent?data=' + data
    async with aiohttp.ClientSession() as session:
        async with session.get(curl) as res:
            dic = await res.json()
            content = dic['data']['novel']['content']
            # print(content)
            content = dic['data']['novel']['content']
            async with aiofiles.open("Novel/" + c_name, mode='w', encoding='utf-8') as f:
                await f.write(content)


async def getcatalog(url, book_id):
    res = requests.get(url)
    items = res.json()['data']['novel']['items']

    tasks = []
    for item in items:
        tasks.append(asyncio.create_task(download_content(book_id, item['cid'], item['title'])))
    await asyncio.wait(tasks)


if __name__ == '__main__':
    book_id = '4355370985'
    url = 'https://dushu.baidu.com/api/pc/getCatalog?data={"book_id":' + book_id + '}'
    lo = asyncio.new_event_loop()
    asyncio.set_event_loop(lo)
    lo.run_until_complete(getcatalog(url, book_id))

