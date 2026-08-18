import time

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

service = Service(executable_path="chromedriver-mac-x64/chromedriver")
driver = webdriver.Chrome(service=service)

driver.get("https://google.com")

WebDriverWait(driver, 5).until(
    EC.presence_of_element_located((By.CLASS_NAME, "gLFyf"))
    # if after 5 sec, the program does not exist crash the program
)

input_element = driver.find_element(By.CLASS_NAME, "gLFyf")
input_element.clear()
input_element.send_keys('tech with tim' + Keys.ENTER)

WebDriverWait(driver, 5).until(
    EC.presence_of_element_located((By.PARTIAL_LINK_TEXT, "Tech With Tim"))
    # if after 5 sec, the program does not exist crash the program
)

link = driver.find_element(By.PARTIAL_LINK_TEXT, "Tech With Tim")
link.click()

time.sleep(10)

driver.quit()
