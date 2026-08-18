from tkinter import *
from tkinter.ttk import *
import time


def Start():
    GB = 100
    download = 0
    speed = 1

    def update_progress():
        nonlocal download, GB
        if download < GB:
            time.sleep(0.05)
            bar['value'] += (speed / GB) * 100
            download += speed
            percent.set(str(int(download / GB * 100)) + "%")
            text.set(str(download) + "/" + str(GB) + "GB completed")
            window.after(50, update_progress)  # schedule the function to be called again after 50 milliseconds

    update_progress()  # start the download simulation


window = Tk()

percent = StringVar()
text = StringVar()

bar = Progressbar(window, orient=HORIZONTAL, length=300)
bar.pack(pady=10)

percentlabel = Label(window, textvariable=percent)
percentlabel.pack()
tasklabel = Label(window, textvariable=text)
tasklabel.pack()

button = Button(window, text="Download", command=Start).pack()
window.mainloop()
