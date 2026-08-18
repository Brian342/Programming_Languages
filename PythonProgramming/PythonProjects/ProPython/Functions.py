# function = a block of code which is executed only when it is called.
# def example():
#     pass
#
#
# print(type(example()))
# print(example())


# def add_prefix(string):
#     """Adds a pro_' prefix before the string provided."""
#     return 'pro_' + string
#
#
# print(add_prefix('python'))


def add_prefix(string, prefix='pro_'):
    """Adds a 'pro_' prefix before the string provided."""
    return prefix + string


print(add_prefix('python'))
