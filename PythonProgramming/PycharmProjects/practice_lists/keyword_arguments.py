# keyword argument = arguments preceded by an identifier when we pass them to a function.
#                   The order of the argument doesn't matter, unlike positional arguments
#                   python knows the names of the arguments that our function receives

def hello(first, middle, last):
    print("Hello " + first, " " + middle, " " + last)


hello(middle="Kyalo", first="brian", last="Kimanzi")

