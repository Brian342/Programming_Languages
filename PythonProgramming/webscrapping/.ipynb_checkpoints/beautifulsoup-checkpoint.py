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
html_text = requests.get("https://www.timesjobs.com/candidate/job-search.html?searchType=personalizedSearch&from=submit&searchTextSrc=&searchTextText=&txtKeywords=python&txtLocation=").text
soup = BeautifulSoup(html_text, "lxml")
job = soup.find("li", class_="clearfix job-bx wht-shd-bx")
company_name = job.find("h3", class_="joblist-comp-name").text.strip()
skills1 = job.find("div", class_="srp-skills").text.strip()

print(f'''
Company Name:\n {company_name} 
Required Skills: {skills1}
''')