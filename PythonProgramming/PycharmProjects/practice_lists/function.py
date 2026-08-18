# A program is required that accepts three integers and finds
# the smallest.
# i) Write a program with a function called small
# which accepts the three integers entered via the main
# function and returns the smallest. The main function should
# output the three numbers entered together with the smallest.
# ii) Write a program with a function called smallest which
# accepts three integers entered via the main function and prints
# them together with the smallest.
def small(num1, num2, num3):

    if num1 < num2 and num1 < num3:
        print("num1 is the smallest!!")
    elif num2 < num1 and num2 < num3:
        print("num2 is the smallest!!")
    elif num3 < num1 and num3 < num2:
        print("num3 is the smallest!!")
    else:
        print("Not a number!!!")
        return small(num1, num2, num3)


num1 = int(input("Enter num1: "))
num2 = int(input("Enter num2: "))
num3 = int(input("Enter num3: "))

smallest = small(num1, num2, num3)
output = f'Num1 value is {num1}; Num2 value is {num2}; Num3 value is {num3}:'
print(output)


