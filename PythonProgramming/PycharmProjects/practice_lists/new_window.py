from tkinter import *


# Toplevel()= new window on top of other windows, linked to a bottom window
# Tk() = new independent window

def create_window():
    new_window = Toplevel()

    # old_window.destroy()  # close out old_window


old_window = Toplevel()
Button(old_window, text="create new window", command=create_window).pack()
old_window.mainloop()
