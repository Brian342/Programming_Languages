from tkinter import *
from tkinter import colorchooser  # submodule


def click():
    # color = colorchooser.askcolor()
    #colorHex = color[1]
    # window.config(bg=color[1])  # change the background of the window
    window.config(bg=colorchooser.askcolor()[1])


window = Tk()
window.geometry("420x420")
button = Button(text='Click me', command=click)
button.pack()
window.mainloop()
