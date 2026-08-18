from bs4 import BeautifulSoup
import requests

source = requests.get('http://coreyms.com').text
soup = BeautifulSoup(source, 'lxml')

# print(soup.prettify())

article = soup.find('article')

print(article.prettify())
# for article in soup.find_all('div', class_='article'):
#     pass