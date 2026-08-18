# # filter() = creates a collection of elements from an iterable for which a function returns true
#
# # filter(function, iterable)

friends = [("Rachel", 19),
           ("monica", 18),
           ("phoebe",   17),
           ("joey", 16),
           ("chandler", 21),
           ("ross", 20)]
age = lambda data: data[1] >= 18

drinking_buddies = list(filter(age, friends))

for i in drinking_buddies:
    print(i)

