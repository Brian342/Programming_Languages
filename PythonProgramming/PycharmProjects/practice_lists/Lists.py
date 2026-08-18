# lists = used to store multiple items in a single variable

food = ["pizza", "hamburger", "pilau", "spaghetti"]

food[0] = "chips"
# print(food[0])

food.append("ice cream")  # used to add elements in the list.
food.remove("hamburger")  # used to remove elements in the list.
food.pop()  # used to remove the last element in the list.
food.insert(0, "cake")  # used to add element in the list.
food.sort()  # used to sort the list alphabetically.
food.clear()    # used to clear all items in the list.
for x in food:
    print(x, end=" ")
