from tkinter import *


def submit():
    print("The temperature is: " + str(scale.get()) + " degrees C")


window = Tk()

hotImage = PhotoImage(file='flame.png')
hotLabel = Label(image=hotImage)
hotLabel.pack()


scale = Scale(window, from_=0,
              to=100,
              length=600,
              orient=VERTICAL,  # orientation of scale
              font=('consolas', 20),
              tickinterval=10,  # numerical indicators on the scale.
              # showvalue=0,  # hide current value
              resolution=5,  # increment of slider
              troughcolor='#69EAFF',
              fg='#FF1C00',
              bg='black',

              )
scale.set(((scale['from'] - scale['to']) / 2) + scale['to'])  # set current value of slider
scale.pack()

snowImage = PhotoImage(file='snowflake.png')
snowLabel = Label(image=snowImage)
snowLabel.pack()

button = Button(window, text='submit', command=submit)
button.pack()

window.mainloop()
