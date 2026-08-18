try:
    with open('text.txt') as file:
        print(file.read())
except FileNotFoundError:
    print("That file was not found!!")


# print(file.closed)
