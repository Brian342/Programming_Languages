# set = collection which is unordered, un-indexed.
#        they do not have duplicate values.

utensils = {"fork", "spoon", "knife"}
dishes = {"bowl", "plate", "cup", "knife"}

# utensils.add("napkins")  # used to add elements in the set
# utensils.remove("fork")  # used to remove an element in the set
# utensils.clear()  # used to all elements in the set.
# utensils.update(dishes)  # used to add all the elements in dishes to utensils
# dinner_table = utensils.union(dishes) # used to join both elements together.
# print(utensils.difference(dishes))  # Return the difference of two or more sets as a new set.
#                                  (i.e. all elements that are in this set but not the others.)
print(utensils.intersection(dishes))  # Return the intersection of two sets as a new set.
#                                   (i.e. all elements that are in both sets.
# for x in dinner_table:
#     print(x, end=" ")
