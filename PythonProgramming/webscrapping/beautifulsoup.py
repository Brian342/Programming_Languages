from bs4 import BeautifulSoup
import requests

# with open("home.html", "r") as html_file:
#     content = html_file.read()
#     soup = BeautifulSoup(content, "lxml")
#     course_cards = soup.find_all('div', class_='card')
#     for course in course_cards:
#         course_name = course.h5.text
#         course_price = course.a.text.split()[-1]
#         print(f"{course_name} costs {course_price}")

# Real website
html_text = requests.get("https://www.shine.com/job-search/python-jobs?q=python").text
soup = BeautifulSoup(html_text, "lxml")
tags = soup.find_all("div", class_="parentClass position-relative")
for tag in tags:
    published_day = tag.find("div", class_="jobCard_jobCard_features__wJid6").span.text
    if 'few' in published_day:
        job = tag.find("strong", class_="jobCard_pReplaceH2__xWmHg").text  # returns the first div
        company_name = tag.find("div", class_="jobCard_jobCard_cName__mYnow").text
        skill = tag.find("div", class_="jobCard_skillList__KKExE").text.replace(" ", "")[3::]
        print(f'''
Company Name:\n{company_name}
Required Skills:\n{skill}
published_day:\n{published_day}
    ''')

        print(' ')

# attribute = soup.find("div", class_="parentClass position-relative").text
# print(attribute)

