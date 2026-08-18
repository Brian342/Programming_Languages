# def test_value(value):
#     if value < 100:
#         return 'The value is just right'
#     else:
#         return 'The value is too big'

# simple way
# def test_value(value):
#     return 'The value is ' + ('Just right.' if value < 100 else 'too big!')
#
#
# print(test_value(101))

# try:
#     age = int(input('Enter your age! '))
#
#     if age > 18:
#         print('qualified to vote')
#     else:
#         print('minor')
#
# except KeyboardInterrupt:
#     print('\nYou interrupted the program')
# finally:
#     print('This code will always execute!')

# try:
#     age = int(input('Enter your age! '))
#     print('qualified to vote ' if age > 18 else 'minor')
# except KeyboardInterrupt:
#     print('\nYou terminated the program')
# finally:
#     print('This code will always execute! ')


def test_value(value):
    return 'the value is ' + (value < 100 and 'just right. ' or 'too big!')


print(test_value(211))
