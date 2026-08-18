import random

R_EATING = "I don't like eating anything because I'm a bot obviously!"
R_SEARCH = "I can't help you with that may be try searching through the web!"
R_JOKE = "Your Love Life is a joke!"


def unknown():
    response = ['could you please re-phrase that?',
                "...",
                "Sounds about right",
                "What the hell does that mean?"][random.randrange(4)]
    return response
