from tkinter import *


# entry widget = textbox that accepts a single line of user input
def submit():
    username = entry.get()
    print("Hello " + username)
    entry.config(state=DISABLED)


def delete():
    entry.delete(0, END)


def Backspace():
    entry.delete(len(entry.get()) - 1, END)


window = Tk()
entry = Entry(window,
              font=("Time new Roman", 50),
              fg="#00FF00",
              bg="black", )
# entry.insert(0, 'Enter your username')
# entry.config(show="*")
# entry.config(state=DISABLED)
entry.pack(side=LEFT)
submit_button = Button(window, text="Submit", command=submit)
submit_button.pack(side=RIGHT)

delete_button = Button(window, text="Delete", command=delete)
delete_button.pack(side=RIGHT)

backspace_button = Button(window, text="BackSpace", command=Backspace)
backspace_button.pack(side=RIGHT)
window.mainloop()


