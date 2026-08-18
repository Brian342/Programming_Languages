# sort() method = used with lists
# sort() function = used with iterables

# students = ["squidward", "sandy", "patrick", "spongebob", "mr.krabs"]
#
# # students.sort(reverse=True)
# sorted_students = sorted(students,reverse=True)
#
# for i in sorted_students:
#     print(i)

students = [("squidward", "F", 60),
            ("sandy", "A", 33),
            ("patrick", "D", 36),
            ("spongebob", "B", 20),
            ("Mr.krabs", "C", 78)]

ages = lambda age: age[2]
# students.sort(key=grade, reverse=True)
sorted_students = sorted(students, key=ages)   # sorted list with iterables
for i in students:
    print(i)
