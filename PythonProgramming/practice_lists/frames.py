# frame = a rectangular container to group and hold widgets

from tkinter import *

window = Tk()
frame = Frame(window, bg="grey", bd=5, relief=RAISED)
frame.pack(side=BOTTOM)

Button(frame, text="W", font=("Consolas", 25), width=3).pack(side=TOP)
Button(frame, text="A", font=("Consolas", 25), width=3).pack(side=LEFT)
Button(frame, text="S", font=("Consolas", 25), width=3).pack(side=LEFT)
Button(frame, text="D", font=("Consolas", 25), width=3).pack(side=LEFT)

window.mainloop()
