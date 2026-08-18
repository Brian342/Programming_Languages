import random

guess = random.randint(1, 6)  # random integer
y = random.random()  # random float number

mylist = ["rock", "paper", "scissors"]
z = random.choice(mylist)

cards = [1, 2, 3, 4, 5, 6, 7, 8, 9, 'J', 'Q', 'K', 'A']

random.shuffle(cards)
print(cards)
