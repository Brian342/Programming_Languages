# Write a program that allows the user to enter eight integer values.
# Display the values in the reverse order of the order they were
# Entered.


# reversed_number = 0
#
# number = int(input("Enter a number->"))
#
# while number != 0:
#     reversed_number *= 10
#     lastDigit = number % 10
#     reversed_number += lastDigit
#     number /= 10
# output = f'The reverse of the digit is->{reversed_number}'
# print(output)


#  Write a program that allows the user to enter two double values.
# Display one of three messages: “The first number you entered
# is larger”, “The second number you entered is larger”, or
#  “The numbers are equal”.


num1 = float(input("Enter  value 1->"))
num2 = float(input("enter value 2->"))

if num1 > num2:
    print("num1 is larger!!")
elif num2 > num1:
    print("num2 is larger!!")
elif num1 == num2 or num2 == num1:
    print("The numbers are equal")
else:
    print("invalid!!")

