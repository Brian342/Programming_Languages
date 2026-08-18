# 6) Write a program that uses a function called calc_vol
# to compute the volume of a cube as vol = height * length * breadth.
# The program should output both the dimensions and result.

def calc_vol(height, length, breadth):
    # volume = height * length * breadth
    return height * length * breadth


height = int(input("Enter the height-> "))
length = int(input("Enter the height-> "))
breadth = int(input("Enter the height-> "))

volume = calc_vol(height, length, breadth)
print(f'The height of the cube is->{height};\n '
      f'The length of the cube is->{length};\n'
      f'The breadth of the cube is->{breadth}')
print("The volume of the cube is->" + str(volume))
