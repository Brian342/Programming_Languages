import pandas as pd
import selenium
from bs4 import BeautifulSoup
import time, datetime
import requests
import smtplib
import numpy as np


def check_items():
    url = 'https://www.jumia.co.ke/fashion-men-pants-trousers-casual-pants-cargo-pants-67961216.html'

    headers = {
        "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36", }

    page = requests.get(url, headers=headers)

    soup1 = BeautifulSoup(page.content, "html.parser")
    soup2 = BeautifulSoup(soup1.prettify(), "html.parser")

    title = soup2.find(class_='-fs20 -pts -pbxs').get_text(strip=True)
    price = soup2.find(class_='-b -ubpt -tal -fs24 -prxs').get_text(strip=True)
    price = price.strip()[4:]
    print("Title :", title)
    print("Price :", price)

    import datetime
    today = datetime.date.today()

    import csv
    # head = ['Title', 'Price', 'Date']
    data = [title, price, today]
    with open('JumiaScraping.csv', 'a+', newline='', encoding='UTF8') as f:
        writer = csv.writer(f)
        # writer.writerow(head)
        writer.writerow(data)

    if price <= "721":
        send_mail()

    df = pd.read_csv('/Users/briankimanzi/Documents/python /E_commerceScraping/JumiaScraping.csv')
    print(df)


print(check_items())

while True:
    check_items()
    time.sleep(86400)


def send_mail():
        # Connect to the Gmail SMTP server with SSL
        server = smtplib.SMTP_SSL('smtp.gmail.com', 465)
        # Log in to the email account
        server.login('migelbrian3@gmail.com', '673GezMigel')

        # Email details
        subject = 'The shirt you want is below $15! Now is your chance to buy!'
        body = (
            "Brian, This is the moment we have been waiting for. "
            "Now is your chance to pick up the shirt of your dreams. Don't miss it!"
        )
        msg = f'Subject: {subject}\n\n{body}'

        # Send the email (from, to, message)
        server.sendmail(
            'migelbrian3@gmail.com',  # Sender
            msg  # Message
        )

        print("Email sent successfully!")

    # except Exception as e:
    #     print(f"Failed to send email: {e}")
    #
    # finally:
    #     # Always close the connection to the server
    #     server.quit()

# Call the function
print(send_mail())