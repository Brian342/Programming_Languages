# courses = ['History', 'Math', 'Physics', 'CompSci']
#
# print(courses[0])

def hello_func(name, greeting="Hello"):
    return f"{greeting}, {name}"


print(hello_func('Brian'))


# args and kwargs arguments
def student_info(*args, **kwargs):
    print(args)
    print(kwargs)


courses = ["Math", "Arts", "English"]
details = {"Name": ["James",  "Paul", "Peter"], "Age": [24, 22, 21]}

student_info(*courses, **details)
