text = "Mr Owen: I'm doing fine what about you?"

# with open('text.txt', 'w') as file:  # w for writing files
#     file.write(text)

with open('text.txt', 'a') as file:  # a for appending a file
    file.write(text)
