# 1) Write a program that computes both the area and circumference of a circle using functions.
import math


def cal_area(radius):
    return 2 * math.pi * math.pow(radius, 2)


def cal_circumference(radius):
    return 2 * math.pi * radius


radius = float(input(str("Enter the radius of the circle: ")))

print(f"The radius is {radius} The area is {math.floor(cal_area(radius))} The circumference is {math.floor(cal_circumference(radius))}")
