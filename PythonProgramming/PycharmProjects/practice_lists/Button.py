from tkinter import *

# button = you click it, then it does stuff

count = 0


def click():
    global count
    count += 1
    print("You clicked the button!", count, " times")


window = Tk()
photo = PhotoImage(file='image.png')
button = Button(window,
                text="Click me!",
                command=click,
                font=("comic sans", 30),
                fg="green",
                bg="black",
                bd=2.1,
                activeforeground="green",
                activebackground="black",
                state=ACTIVE,
                image=photo,
                compound='bottom')
window.config(background="sky blue")
button.pack()
window.mainloop()
