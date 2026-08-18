# A program is required that accepts marks in three subjects
# and calculates the average mark. The program then assigns
# the student a grade based on the average mark using the grading system below.
# Average Mark              Grade
# 80 – 100                     A
# 70 – 80                      B
# 60 – 70                      C
# 50 – 60                      D
# 0 – 50                       E
# i) Write a program with a function called get_grade
# which accepts the average mark and returns the grade to
# the main function which then outputs it.
# ii) Write a program with a function called grade
# which accepts the average mark and prints the grade
def get_grade(subj1, subj2, subj3):
    sum = subj1 + subj2 + subj3
    return sum


def grade(sum):
    average = sum / 3
    if 80 < average <= 100:
        print("You have an A")
    elif 70 < average <= 80:
        print("You have an B")
    elif 60 < average <= 70:
        print("You have an c")
    elif 50 < average <= 60:
        print("You have an D")
    elif 0 <= average <= 50:
        print("You have an E")
    else:
        print("Not on the grade system: ")
    return average


subj1 = int(input("Enter subject1: "))
subj2 = int(input("Enter subject2: "))
subj3 = int(input("Enter subject3: "))

sum = int(get_grade(subj1, subj2, subj3))
average = int(grade(sum))

print(f'The sum is->{sum};\nThe average is->{average}:')



