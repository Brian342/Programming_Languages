output = {value: str(value) for value in range(10) if value > 5}
print(output)

result = dict((value, str(value)) for value in range(10) if value > 5)
print(result)
