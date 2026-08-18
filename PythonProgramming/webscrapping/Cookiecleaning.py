from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

service = Service(executable_path=r"chromedriver-mac-x64/chromedriver")
driver = webdriver.Chrome(service=service)

driver.get("https://orteil.dashnet.org/cookieclicker/")

cookie_id = "bigCookie"
cookies_id = "cookies"

WebDriverWait(driver, 5).until(
    EC.presence_of_element_located((By.XPATH, "//*[contains(text(), 'English')]"))
    # if after 5 sec, the program does not exist crash the program
)

languages = driver.find_element(By.XPATH, "//*[contains(text(), 'English')]")
languages.click()

WebDriverWait(driver, 5).until(
    EC.presence_of_element_located((By.ID, cookie_id))
    # if after 5 sec, the program does not exist crash the program
)

cookie = driver.find_element(By.ID, cookie_id)
cookie.click()

time.sleep(10)

while True:
    cookie.click()
    cookies_count = driver.find_element(By.ID, cookies_id).text.split(" ")[0]
    cookies_count = cookies_count.replace(",", "")
    print(cookies_count)
