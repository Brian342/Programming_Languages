# loop control statement = change a loop execution from its normal sequence
# break = used to terminate the loop entirely
# continue = skips to the next iteration of the loop.
# pass = does nothing, acts as a placeholder

phone_number = "123-456-7890"

for i in phone_number:
    if i == "-":
        continue
    print(i, end="")
