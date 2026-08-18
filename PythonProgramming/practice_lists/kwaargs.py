# **kwargs = parameter that will pack all arguments into a dictionary
#            useful so that a function can accept a varying of keyword argument

def hello(**kwaargs):
    # print("Hello " + kwaargs['first'] + " " + kwaargs['last'])
    print("Hello", end=" ")
    for key, value in kwaargs.items():
        print(value, end=" ")


hello(first="Brian", middle="kyalo", last="kimanzi")
