# def unique_letters(word):
#     return set(word.lower())
#
#
# print(unique_letters('spam'))
# print(unique_letters('eggs'))

example = {1, 2, 3, 4, 5}
var = 4 in example

print(var)

var2 = 6 in example

print(var2)

example.add(6)
print(example)
example.update({6, 7, 8, 9})
print(example)
example.remove(9)
print(example)
# example.remove(9)
# print(example)

example.discard(8)
print(example)
example.discard(8)
print(example)

example.pop()
print(example)

example.clear()
print(example)

output = {1, 2, 3} | {4, 5, 6}
print(output)
output2 = {1, 2, 3}.union({4, 5, 6})
print(output2)

output = {1, 2, 3, 4, 5} & {4, 5, 6, 7, 8}
print(output)
output2 = {1, 2, 3, 4, 5}.intersection({4, 5, 6, 7, 8})
print(output2)

output = {1, 2, 3, 4, 5} - {2, 4, 6}
print(output)
output2 = {1, 2, 3, 4, 5}.difference({2, 4, 6})
print(output2)

output = {1, 2, 3, 4, 5} ^ {4, 5, 6}
print(output)
output2 = {1, 2, 3, 4, 5}.symmetric_difference({4, 5, 6})
print(output2)

result = {1, 2, 3}.issubset({1, 2, 3, 4, 5})
print(result)
result2 = {1, 2, 3, 4, 5}.issubset({1, 2, 3})
print(result2)

result = {1, 2, 3}.issuperset({1, 2, 3, 4, 5})
print(result)
result2 = {1, 2, 3, 4, 5}.issuperset({1, 2, 3})
print(result2)

result = not({1, 2, 3} - {1, 2, 3, 4, 5})
print(result)
result2 = not({1, 2, 3, 4, 5} - {1, 2, 3})
print(result2)
