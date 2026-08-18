from tkinter import *


def doSomething(event):
    label.config(text=event.keysym)
    print("You pressed: " + event.keysym)


window = Tk()

window.bind("<Return>", doSomething)
window.bind("<Key>", doSomething)
label = Label(window, font=("Helvetica", 100))
label.pack()

window.mainloop()
