from tkinter import *


def drag_start(event):
    widget = event.widget
    widget.startX = event.x  # place we click within the window itself = were we begin dragging the window to
    widget.startY = event.y


def drag_motion(event):
    widget = event.widget
    x = widget.winfo_x() - widget.startX + event.x   # gets the top left x-coordinate relative to the window we are in
    y = widget.winfo_x() - widget.startY + event.y
    widget.place(x=x, y=y)


window = Tk()
label = Label(window, bg="red", width=10, height=5)
label.place(x=0, y=0)

label2 = Label(window, bg="blue", width=10, height=5)
label2.place(x=100, y=100)

label.bind("<Button-1>", drag_start)  # label.bind(event,function)
label.bind("<B1-Motion>", drag_motion)

label2.bind("<Button-1>", drag_start)  # label.bind(event,function)
label2.bind("<B1-Motion>", drag_motion)
window.mainloop()
