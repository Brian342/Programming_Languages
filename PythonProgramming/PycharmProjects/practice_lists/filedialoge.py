from tkinter import *
from tkinter import filedialog


def openfile():
    filepath = filedialog.askopenfilename(
        initialdir="/Users/briankimanzi/Documents/python/PycharmProjects/practice_lists",
        title="Open file Okay?",
        filetypes=(("text files", "*.txt"),
                   ("all files", "*.*")))
    file = open(filepath, 'r')
    print(file.read())
    file.close()


window = Tk()
button = Button(text="Open", command=openfile)
button.pack()
window.mainloop()
