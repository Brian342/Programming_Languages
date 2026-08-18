from collections import OrderedDict
d = OrderedDict((value, str(value)) for value in range(10) if value > 5)
print(d)
d[10] = 10
print(d)
OrderedDict([(6, '6'),(7, '7'), (8, '8'), (9, '9'), (10, '10')])
del d[7]
print(d)
