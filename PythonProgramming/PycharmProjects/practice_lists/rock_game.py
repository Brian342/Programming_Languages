import random

while True:
    choices = ['rock', 'paper', 'scissors']

    computer = random.choice(choices)
    player = None
    while player not in choices:
        player = input("choose one!- rock, paper or scissors?:").lower()

    if player == computer:
        print("Computer:", computer)
        print("player: ", player)
        print("Tie!")
# rock
    elif player == "rock":
        if computer == "paper":
            print("Computer:", computer)
            print("player: ", player)
            print("Computer wins!")
        if computer == "scissors":
            print("Computer:", computer)
            print("player: ", player)
            print("you win!")
# paper
    elif player == "paper":
        if computer == "scissors":
            print("Computer:", computer)
            print("player: ", player)
            print("Computer wins!")
        if computer == "rock":
            print("Computer:", computer)
            print("player: ", player)
            print("You win!")


# scissors
    elif player == "scissors":
        if computer == "paper":
            print("Computer:", computer)
            print("player: ", player)
            print("You win!")
        if computer == "rock":
            print("Computer:", computer)
            print("player: ", player)
            print("Computer wins!")
    play_again = input("Play again? (yes/no): ").lower()
    if play_again != "yes":
        break
print("kwera kabisa failure! failure")
