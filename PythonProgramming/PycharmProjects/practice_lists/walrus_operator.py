# walrus operator :=
# new to python 3.8
# also known as the assignment expression aka walrus operator
# assigns values to variables as part of a larger expression

# happy = True
# print(happy)
# print(happy := True)

# foods = list()
# while True:
#     food = input("What food do you like?: ")
#     if food == "Quit":
#         break
#     foods.append(food)
foods = list()
while food := input("What food do you like?: ") != "quit":
    foods.append(food)