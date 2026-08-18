# 5) Write a program that accepts 3 floating-point
# numbers and outputs the biggest.
# The program should make use of a (user-defined)
# function called get_big, which accepts the three numbers
# entered from main and then returns the biggest.
# The main function should then output the three numbers entered
# together with the biggest.


def get_big(num1, num2, num3):
    if num1 > num2 and num1 > num3:
        print("Num1 is the biggest!!")
    elif num2 > num1 and num2 > num3:
        print("Num2 is the biggest!!")
    elif num3 > num2 and num3 > num1:
        print("Num3 is the biggest!!")
    else:
        print("not a number!!")
        return get_big(num1, num2, num3)


num1 = float(input("Enter the first number! "))
num2 = float(input("Enter the second number! "))
num3 = float(input("Enter the third number! "))

print(f'The first number is->{num1}\n'
      f'The second number is->{num2}\n'
      f'The third number is->{num3}:')
biggest = get_big(num1, num2, num3)
