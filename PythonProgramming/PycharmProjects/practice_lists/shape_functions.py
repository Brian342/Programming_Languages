# 7) Write a program that computes the area of either a rectangle,
# a circle or a right-angled triangle. The program should display a
# menu that enables the user to select the type of figure whose area
# he/she wants to compute. Depending on the users choice, the program
# should prompt for the dimensions and perform the computations.
# The output should be: - The type of figure, the dimensions and the
# area. Define three functions: - one to compute the area of a rectangle,
# one the area of a circle and one the area of a triangle.
# (NB: 1. The calculation should be for only one figure at any one time.
# 2. Computations should be done in the user-defined functions.)
#  area = base + height + sqrt(pow(base , 2) + pow(height,2));
import math


def cal_rectangle(length, width, height):
    area1 = length * width * height
    return area1


def cal_circle(radius):
    pi = 3.142
    area2 = pi * math.pow(radius, 2)
    return area2


def cal_right_triangle(base, height):
    area3 = base + height + math.sqrt(math.pow(base, 2)) + math.pow(height, 2)
    return area3


print("figures for choosing to calculate area!!\n"
      "1)       Rectangle\n"
      "2)       circle\n"
      "3)       right-angled-triangle\n")
choice = int(input("Kindly select your choice (in number)->"))

if choice == 1:
    length = int(input("Enter the length->"))
    width = int(input("Enter the width->"))
    height = int(input("Enter the height->"))
    area1 = cal_rectangle(length, width, height)

    print(f'The figure selected is->Rectangle;\n'
          f'The length is->{length};\n'
          f'The width is->{width};\n'
          f'The height is->{height};\n')
    print("The area of the rectangle is->" + str(area1))

elif choice == 2:
    radius = int(input("Enter the radius->"))
    area2 = cal_circle(radius)

    print(f'The figure selected is->circle\n'
          f'The radius is {radius}:')
    print("The area of the circle is->" + str(area2))

elif choice == 3:
    base = int(input("Enter the base->"))
    height = int(input("Enter the height->"))
    area3 = cal_right_triangle(base, height)

    print(f'The figure selected is->Right_angled_triangle\n'
          f'The base is->{base};\n'
          f'The height is->{height}:')

    print("The area of the right_angled_triangle is->" + str(area3))

else:
    print("Not on the choice!!")
