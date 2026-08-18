# output = []
# for value in range(10):
#     if value > 5:
#         output.append(str(value))
#
# print(output)

# simple way
output = [str(value) for value in range(10) if value > 5]
print(output)

# minimum value
output = min([value for value in range(10) if value > 5])
print(f"The minimum value in the list is {output}")

# maximum value
output = max([value for value in range(10) if value > 5])
print(f"The maximum value on the list is {output}")
