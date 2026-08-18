# tuple = collection which is ordered and unchangeable
#         used to group together related data.

student = ("Brian", 21, "male")

print(student.count(21))  # used to show how many times the string/integer appears on the tuple
print(student.index("male"))

for x in student:
    print(x, end=" ")
print("\n")
if "brian" in student:
    print("brian is present")
else:
    print("brian is absent")
