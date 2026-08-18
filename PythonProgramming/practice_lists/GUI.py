# Tkinter =  is a python binding to the TK GUI toolkit. it is the standard python interface to the
#             Tk GUI toolkit and is python's de facto standard GUI.
from tkinter import *

# widgets = GUI elements: buttons,textboxes,labels, images
# windows = serves as a container to hold or contain these widgets
window = Tk()  # instantiate an instance of a window
window.geometry("420x420") # adjusting the height and width of the window
window.title("Brian's first GUI program")  # putting a name on the window head

icon = PhotoImage(file='image.png')
window.iconphoto(True, icon)  # putting an image on the header of the window
window.config(background="#5cfcff")  # background color for the window

window.mainloop()  # place window on computer screen, listen for events
