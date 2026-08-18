from tkinter import *


# grid()= geometry manager that organizes widgets in a table-like structure in a parent
def Submit_value():
    print("Your name is: ", LastNameEntry)


window = Tk()

titleLabel = Label(window, text="Enter your info: ", font=("Ariel", 25)).grid(row=0, column=0, columnspan=2)
firstNameLabel = Label(window, text="First name: ", width=20, bg="red").grid(row=1, column=0)
firstNameEntry = Entry(window).grid(row=1, column=1)

LastNameLabel = Label(window, text="Last Name: ", bg="green").grid(row=2, column=0)
LastNameEntry = Entry(window).grid(row=2, column=1)

emailLabel = Label(window, text="Email: ", bg="blue", width=30).grid(row=3, column=0)
emailEntry = Entry(window).grid(row=3, column=1)

submitButton = Button(window, text="Submit", command=Submit_value).grid(row=4, column=0, columnspan=2)

window.mainloop()
