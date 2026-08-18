# numbers = [10, 2, 3, 5, 6, 7, 15]
# maximum = numbers[0]
# for number in numbers:
#     if number > maximum:
#         maximum = number
# print(f"The maximum number is {maximum}")


def find_max(numbers):
    maximum = numbers[0]
    for number in numbers:
        if number > maximum:
            maximum = number
    return f"The maximum number is {maximum}"


def function_name(name="Brian", Age=22):
    detail_name = input("Enter Name: ")
    detail_age = input("Enter the Age: ")
    return f"Name: {detail_name} and Age: {detail_age}"


details = function_name()
print(details)
# print(function_name(name="Peter"))
