from tkinter import *
from tkinter import messagebox  # imports messagebox library


def click():
    # messagebox.showinfo(title='This is an info message box', message='You are a person')
    # while True:
    # messagebox.showwarning(title='WARNING', message='You have a VIRUS!!!')
    # messagebox.showerror(title='ERROR', message='Something went wrong :(')

    # if messagebox.askokcancel(title='Ask ok cancel', message='Do you want to do the thing'):
    #     print('You did the thing!')
    # else:
    #     print('You canceled the thing! :(')

    # if messagebox.askretrycancel(title='Ask ok cancel', message='Do you want to retry the thing'):
    #     print('You retried the thing!')
    # else:
    #     print('You canceled the thing! :(')

    # if messagebox.askyesno(title='Ask yes or no', message='Do you like cake?'):
    #     print('I like cake too :)')
    # else:
    #     print('Why do you not like cake? :(')

    # answer = (messagebox.askquestion(title='ask question', message='Do you like pie?'))
    # if answer == 'yes':
    #     print('I like pie too :)')
    # else:
    #     print('Why do you not like pie? :(')

    answer = messagebox.askyesnocancel(title='Yes No Cancel', message='Do you like to code?',
                                       icon='error')  # icon='warning','info'

    if answer:  # if answer == True
        print("You like to Code :)")
    elif answer == False:  # elif Not answer
        print("Then why are you watching a video on coding!")
    else:
        print("You have dodged the question ")


window = Tk()

button = Button(window, command=click, text='Click Me!')
button.pack()
window.mainloop()
