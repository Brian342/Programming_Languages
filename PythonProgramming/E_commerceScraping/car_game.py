# command = ""
# attempts = 0
# started = False
# while True:
#     command = input("> ").lower()
#     if command == "start":
#         if started:
#             print('Hey the car has already Started .... What are you doing')
#         else:
#             started = True
#             print("Car started ... ready to go")
#     elif command == "stop":
#         if not started:
#             print("The car already stopped you dump fool")
#         else:
#             started = False
#             print("The car stopped!")
#     elif command == "quit":
#         print("The car stopped")
#         break
#     elif command == "help":
#         print("""Start - to start the car\nStop - The car stopped\n quit - to quit the game""")
#     else:
#         if attempts <= 3:
#             print("Sorry I did not catch that please repeat.")
#             print(f"  number of trial {attempts + 1}")
#         attempts += 1
#         if attempts == 3:
#             print("limit of the game was 3 ... Game over")
#             break
#
#
#
#

# price = [5, 2, 5, 2, 2]
# for value in price:
#     output = ''
#     for repl in range(value):
#         output += 'x'
#     print(output)


# number = [1, 1, 1, 1, 4]
# for value in number:
#     output = ''
#     for repl in range(value):
#         output += 'x'
#     print(output)

# numbers = [1, 5, 7, 40, 30, 6, 20]
# maximum = numbers[0]
# for number in numbers:
#     if number > maximum:
#         maximum = number
# print(f"maximum number: {maximum}")

# removes duplicate values in a list
# numbers = [2, 2, 2, 2, 4, 5, 7, 5]
# unique = []
# for number in numbers:
#     if number not in unique:
#         unique.append(number)
# print(unique)

# Dictionary
# customer = {
#     "Name": "John smith",
#     "Age": 30,
#     "Available": True
# }
# print(customer.get("occupation", "Registry")) # if the key value is not present in customer dictionary, it replaces the default with Registry

# task
phone_number = {
    "1": "one",
    "2": "two",
    "3": "three",
    "4": "three",
    "5": "five",
    "6": "six",
    "7": "seven",
    "8": "eight",
    "9": "nine",
    "0": "zero"
}
phone = input("Enter your phone number: ")
output = ''
for ch in phone:
    output += phone_number.get(ch, '?') + " "
print(output)
