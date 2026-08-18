def echo():
    """Returns everything you type until you press Ctrl-c"""

    while True:
        try:
            print(input('Type something: '))
        except KeyboardInterrupt:
            print()  # Make sure the prompt appears on a new line.
            break


print(echo())