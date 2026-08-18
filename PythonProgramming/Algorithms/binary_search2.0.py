def binary_search(arr, item):
    low = 0  # Low keeps track of which part of the list you'll search in
    high = len(arr) - 1  # High keeps track of which part of the list you'll search in

    while low <= high:  # while you haven't narrowed it down to one element
        mid = (low + high) // 2  # checks the middle element
        guess = arr[mid]
        if guess == item:  # checks if the guess is equal to the item
            return mid  # if the guess is equal it returns the mid
        elif guess > item:
            high = mid - 1
        else:
            low = mid + 1
    return None


my_list = [1, 3, 4, 5, 6, 7]

print(binary_search(my_list, 3))
print(binary_search(my_list, -1))

