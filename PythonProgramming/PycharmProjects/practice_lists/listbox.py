# listbox = A listing of selectable text items within its own container


def submit():
    # print(listbox.get(listbox.curselection()))
    food = []

    for index in listbox.curselection():
        food.insert(index, listbox.get(index))

    print("You have ordered: ")
    for index in food:
        print(index)


def add():
    listbox.insert(listbox.size(), entryBox.get())
    listbox.config(height=listbox.size())


def delete():
    # listbox.delete(listbox.curselection())
    for index in reversed(listbox.curselection()):
        listbox.delete(index)
        # print("You have deleted: ", index)

    listbox.config(height=listbox.size())


from tkinter import *

window = Tk()

listbox = Listbox(window,
                  bg="#f7ffde",
                  font=("constantia", 35),
                  width=12,
                  fg="black",
                  selectmode=MULTIPLE)
listbox.pack()

listbox.insert(1, 'Pizza')
listbox.insert(2, 'Pasta')
listbox.insert(3, 'Garlic bread')
listbox.insert(4, 'Soup')
listbox.insert(5, 'Salad')

listbox.config(height=listbox.size())

entryBox = Entry(window)
entryBox.pack()

submitButton = Button(window, text="Submit", command=submit)
submitButton.pack()

addButton = Button(window, text="Add", command=add)
addButton.pack()

deleteButton = Button(window, text="Delete", command=delete)
deleteButton.pack()

window.mainloop()
