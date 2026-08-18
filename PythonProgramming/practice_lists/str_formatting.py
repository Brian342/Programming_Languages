# # str.format() = optional method that gives users
# #                 more control when displaying output
#
# # animal = "cow"
# # item = "moon"
#
#
# # print("The " + animal + " Jumped over the " + item)
# # print("The {} jumped over the {}".format(animal, item))
# # print("The {1} jumped over the {0}".format(animal, item))  # positional argument
# # print("The {item} jumped over the {animal}".format(animal="cow", item="moon"))  # keyword argument
# # print(f'The {item:>10} jumped over the {animal}')
#
# name = "Brian"
# print(f'Hello, my name is {name:10}. Nice to meet you!!')  # they work the same
# print(f'Hello, my name is {name:<10}. Nice to meet you!!')  # right alignment
# print(f'Hello, my name is {name:>10}. Nice to meet you!!')  # right alignment
# print(f'Hello, my name is {name:^10}. Nice to meet you!!')  # center alignment

number = 3.14159

print(f'The number pi is {number:.3f}')  # converts a float number with the decimal portion
number = 1000
# print(f'The number is {number:,}')
print(f'The number is {number:b}')  # b for binary numbers
# print(f'The number is {number:o}')  # octnumber
# print(f'The number is {number:X}')  # hexadecimal
# print(f'The number is {number:E}')  # scientific
