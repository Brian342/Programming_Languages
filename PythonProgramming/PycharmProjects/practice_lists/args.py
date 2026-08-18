# args = parameter that will pack all arguments into a tuple
#         useful so that a function can accept a varying amount of arguments


def add(*args):
    sum = 0
    args = list(args)  # converting a tuple into list
    args[0] = 2
    for i in args:
        sum += i
    return sum


print(add(1, 2, 4, 3, 6))
