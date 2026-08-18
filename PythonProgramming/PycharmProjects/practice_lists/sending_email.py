import smtplib

sender = "kyalo.kimanzi1@students.jkuat.ac.ke"
receiver = "migelbrian3@gmail.com"
password = "SCt221-0181/2023"
subject = "Python email test"
body = "I wrote an email! :D"

# header
message = f"""From: Kyalo kimanzi{sender}
To: Brian Kimanzi{receiver}
Subject: {subject}\n
{body}
"""
server = smtplib.SMTP("smtp.gmail.com", 587)
server.starttls()
try:
    server.login(sender,password)
    print("Logged in....")
    server.sendmail(sender, receiver, message)
    print("Email has been sent!")

except smtplib.SMTPAuthenticationError:
    print("Unable to sign in")
