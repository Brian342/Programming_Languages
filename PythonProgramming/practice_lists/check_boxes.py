from tkinter import *


def display():
    if x.get() == 1:
        print("You Agree!")
    else:
        print("You don't agree :(")


window = Tk()
x = IntVar()

python_photo = PhotoImage(file='python.png')
check_button = Checkbutton(window,
                           text="I agree to something",
                           variable=x,
                           onvalue=1,
                           offvalue=0,
                           command=display,
                           font=('Time New Roman', 20, 'italic'),
                           fg='green',
                           bg='black',
                           activeforeground='green',
                           activebackground='black',
                           padx=25,
                           pady=10,
                           image=python_photo,
                           compound='left')
check_button.pack()
window.mainloop()
# with the onvalue and off value if you are changing the value from
# int to boolean make sure you change also the variable type .x = Intvar()
# x = booleanvar()
