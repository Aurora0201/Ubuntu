import aiohttp
import asyncio

urls = [
    'https://www.umei.cc/d/file/20220909/8d1c85e87b68cb5e197fdd5895cf3e4f.jpg',
    'https://www.umei.cc/d/file/20220909/288eafe60c362b7445ad30d0b067397f.jpg',
    'https://www.umei.cc/d/file/20220909/c1b43ec8c20f4e429bc9f55ef7c5434d.jpg',
    'https://www.umei.cc/d/file/20220909/2b17459468e4e8fb5c78f36ed1f88aed.jpg',
    'https://www.umei.cc/d/file/20220909/969b470d3cf5097e3b1b339da3ad88ac.jpg',
    'https://www.umei.cc/d/file/20220909/4d222e6a660b046f47c4e024f1ff12c5.jpg',
    'https://www.umei.cc/d/file/20220909/be496b6a466b855e6202b83aff9363ca.jpg',
    'https://www.umei.cc/d/file/20220909/fce617ca5e012eb5ba0a89a101b37362.jpg',
    'https://www.umei.cc/d/file/20220909/371691e1469ea818d60bdb87adeaf2c9.jpg',
    'https://www.umei.cc/d/file/20220909/4b073353faaf82030982b5c4b8f274e8.jpg'
]


async def download(download_url):
    async with aiohttp.ClientSession() as session:
        async with session.get(download_url) as resp:
            img_name = download_url.split("/")[-1]
            with open("img/" + img_name, mode='wb') as f:
                f.write(await resp.content.read())
    print("Done")


async def main():
    tasks = []
    for url_ in urls:
        tasks.append(asyncio.create_task(download(url_)))
    await asyncio.wait(tasks)

if __name__ == '__main__':
    # asyncio.get_event_loop().run_until_complete(main())
    lo = asyncio.new_event_loop()
    asyncio.set_event_loop(lo)
    lo.run_until_complete(main())
