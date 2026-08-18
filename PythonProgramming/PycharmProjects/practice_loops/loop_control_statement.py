# loop control statement = change a loop execution from its normal sequence
# break = used to terminate the loop entirely
# continue = skips to the next iteration of the loop.
# pass = does nothing, acts as a placeholder

while True:
    name = input("Enter your name: ")
    if name != "":
        break
print("Hello " + name)
