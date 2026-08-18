# def count_words(text):
#     count = {}
#     for word in text.split(' '):
#         current = count.get(word, 0)  # make sure we always have a number
#         count[word] = current + 1
#     return count

from collections import defaultdict


def count_words(text):
    count = defaultdict(int)
    for word in text.split(' '):
        count[word] += 1
    return count


print(count_words('Hello world'))
