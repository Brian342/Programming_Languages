from tkinter import *
from time import *


# strftime() a function in the time module, shows current time and how we want the  format to look like
def update():
    time_string = strftime("%I:%M:%S %p")  #
    time_label.config(text=time_string)

    day_string = strftime("%A")  #
    day_label.config(text=day_string)

    date_string = strftime("%B %d, %Y")  #
    date_label.config(text=date_string)

    window.after(1000, update)


window = Tk()
time_label = Label(window, font=("Ariel", 50), fg="red", bg="black")
time_label.pack()

day_label = Label(window, font=("Ink Free", 25))
day_label.pack()

date_label = Label(window, font=("Ink Free", 30))
date_label.pack()

update()
window.mainloop()
