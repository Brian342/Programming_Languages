# def count_lines(sample):
#     """count the number of lines in a file."""
#
#     file = open(sample, 'r')
#     try:
#         return len(file.readlines())
#     finally:
#         file.close()
#
#
# print(count_lines("sample"))

def count_lines(sample):
    """count the number of lines in a file."""
    with open(sample, 'r') as file:
        return len(file.readlines())
