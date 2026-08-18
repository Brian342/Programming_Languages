from tkinter import *
from tkinter import filedialog


def savefile():
    file = filedialog.asksaveasfile(initialdir="/Users/briankimanzi/Documents/python/PycharmProjects/practice_lists",
                                    defaultextension='.txt',
                                    filetypes=[
                                        ("Text file", ".txt"),
                                        ("HTML file", ".html"),
                                        ("All files", ".*")
                                    ])
    if file is None:
        return
    filetext = str(text.get(1.0, END))
    # filetext = input("Enter some text i guess: ")
    file.write(filetext)
    file.close()


window = Tk()
button = Button(text='Save', command=savefile)
button.pack()
text = Text(window)
text.pack()

window.mainloop()
