# Area and Circumference, perimeter  of a Circle, rectangle, Triangle, Square
import math

# Take user choice

def User_choice():
    # while(running=True):
    print("""
            Welcome to simple calculation program for different shapes
            Select your choice to perform operation!!
            1. Circle
            2. Rectangle
            3. Triangle
            4. Square
        """)

    choice = int(input("Enter your choice: "))

    match choice:
        case 1:
            calculate_area, calculate_circumference = Circle()
            print(f"The area of the circle is: {calculate_area:.2f}")
            print(f"The Circumference of the circle is: {calculate_circumference:.2f}")

        case 2:
            calculate_area, calculate_perimeter = Rectangle()
            print(f"The area of the Rectangle is: {calculate_area:.2f}")
            print(f"The Perimeter of the Rectangle is: {calculate_perimeter:.2f}")

        case 3:
            calculate_area, calculate_perimeter = Triangle()
            print(f"The area of the Triangle is: {calculate_area:.2f}")
            print(f"The Perimeter of the Triangle is: {calculate_perimeter:.2f}")
        case 4:
            calculate_area, calculate_perimeter = Square()
            print(f"The area of the Square is: {calculate_area:.2f}")
            print(f"The Perimeter of the Square is: {calculate_perimeter:.2f}")

        case _:
            return "Unknown Case please select available no"


# Perform calculation of each shape
def Circle():
    print("Circle Program")
    radius = int(input("Enter your Radius: "))
    area = math.pi * math.pow(radius, 2)
    circumference = 2 * math.pi * radius
    print()
    return area, circumference


def Rectangle():
    print("Rectangle Program")
    length = int(input("Enter your length: "))
    width = int(input("Enter your Width: "))
    area = width * length
    perimeter = 2 * (width + length)
    print()
    return area, perimeter


def Triangle():
    print("Triangle Program")
    base = int(input("Enter your base: "))
    height = int(input("Enter your height: "))
    side = int(input("Enter your side c: "))
    area = .5 * base * height
    perimeter = base + height + side
    print()
    return area, perimeter


def Square():
    print("Square Program")
    side1 = int(input("Enter side 1 of the square: "))
    area = side1 * 2
    perimeter = 4 * side1
    print()
    return area, perimeter


# print result of user choice
if __name__ == "__main__":
    User_choice()
