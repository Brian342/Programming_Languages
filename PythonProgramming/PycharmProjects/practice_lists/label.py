from tkinter import *


# from PIL import Image

# label = an area widget that holds text and/or an image within a window
def changeImage():
    pass


window = Tk()
photo = PhotoImage(file='rick.png')

label = Label(window, text="I'm The Best",
              font=('Times New Roman', 40, 'italic'),
              fg='green',
              bg='grey',
              relief=SUNKEN,
              bd=10,
              padx=20,
              pady=20,
              image=photo,
              # borderwidth=20,
              compound='top',

              )
label.pack()
# label.place(x=0, y=0)

window.config(background='black')
window.mainloop()
