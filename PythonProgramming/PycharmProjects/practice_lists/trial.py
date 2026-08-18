# #  Write a program that computes both the area
# #  and circumference of a circle using functions.
import math

pi = 3.142


def calculate_area(radius):
    area = pi * math.pow(radius, 2)
    return area


def calculateCircum(radius):
    circumference = 2 * pi * radius
    return circumference


radius = int(input("Enter the radius->"))
area = calculate_area(radius)
circumference = calculateCircum(radius)
print('The radius of the circle is->' + str(radius) + "\nThe area of the circle is->" + str(area) +
      " \nThe circumference of the circle is->" + str(circumference))
