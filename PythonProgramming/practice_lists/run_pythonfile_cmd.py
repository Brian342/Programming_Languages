# ************************************
# run .py file with cmd
# ************************************
# save file as .py (python file)
# go to command prompt
# navigate to directory w/ your file : c:/users/brian/Documents
# invoke python interpreter + script: python hello_world.py

# print("Hello World")
#
# name = input("What is your name!")
#
# print("Hello ", name)
try:
    name = 'Brian'
    print("The first character is:", name[10])
except IndexError:
    print("Dude the program has less characters try from 0-4")


