# higher order function = a function that either:
#                         1. accepts a function as an argument
#                           or 2.
#                         returns a function
#                         (in python, functions are also treated as objects)
# accepts a function as an argument
# def loud(text):
#     return text.upper()
#
#
# def quiet(text):
#     return text.lower()
#
#
# def hello(func):
#     text = func("hello")
#     print(text)
#
#
# hello(loud)
# hello(quiet)

# return a function
def divisor(x):
    def dividend(y):
        return y / x

    return dividend


divide = divisor(2)
result = print
result(divide(10))
