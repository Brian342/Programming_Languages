# scope = The region that a variable is recognized
#          A variable is only available from inside the region it is created
#          A global and locally scoped versions of a variable can be created.

name = "Brian_"  # global scope (available inside & outside functions)


def display_name():
    name = "Kimanzi"  # local scope (available only inside these functions)
    print(name)


display_name()
print(name)
