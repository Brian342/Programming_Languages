"""Data Structure - a data storage format. it is the collection of values
and the format they are stored in, the relationships between the values in
the collection as well as the operations applied on the data stored in the structure.
Operations on data structures
Access and read values
Search for an arbitrary values
Insert values at any point the structure
Delete values in the structure"""

# new_list = [1, 2, 3]
# result = new_list[0]
#
# print(result)

# search algorithm
new_list = [1, 2, 3]
result = new_list[0]

if 1 in new_list: print(True)
for n in new_list:
    if n == 1:
        print(True)

        break

# Inserting(linear Runtime) -adds elements to the beginning of list
# Appending(Constant time operation) -adds elements to the end of list sample numbers.append
# Extend - adding a list to another list
# Delete(Linear Runtime) -
