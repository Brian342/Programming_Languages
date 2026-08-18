from tkinter import *


def openFile():
    print("File has been opened")


def saveFile():
    print("File has been saved")


# editmenu
def cut():
    print("You cut some text ")


def copy():
    print("You copied some text ")


def paste():
    print("You pasted some text ")


window = Tk()
openImage = PhotoImage(file="open.png")
saveImage = PhotoImage(file="save.png")
exitImage = PhotoImage(file="exit.png")

menubar = Menu(window)
window.config(menu=menubar)

filemenu = Menu(menubar, tearoff=0, font=("MV Boli", 15))
menubar.add_cascade(label="File", menu=filemenu)
filemenu.add_command(label="Open", command=openFile, image=openImage, compound='left')
filemenu.add_command(label="Save", command=saveFile, image=saveImage, compound='left')
filemenu.add_separator()
filemenu.add_command(label="Exit", command=quit, image=exitImage, compound='left')

# editmenu
editmenu = Menu(menubar, tearoff=0, font=("MV Boli", 15))
menubar.add_cascade(label="Edit", menu=editmenu)
editmenu.add_command(label="Cut", command=cut)
editmenu.add_command(label="Copy", command=copy)
editmenu.add_command(label="Paste", command=paste)

window.mainloop()
