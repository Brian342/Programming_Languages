from collections import namedtuple
point = namedtuple('point', 'x y')
point = point(13, 25)
print(point)
var = point.x, point.y
print(var)
var2 = point[0], point[1]
print(var2)

