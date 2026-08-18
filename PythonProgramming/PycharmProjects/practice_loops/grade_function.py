# 2) A program is required that accepts marks in three subjects and calculates the average mark. The program then
# assigns the student a grade based on the average mark using the grading system below.
# Average Mark Grade
# 80 – 100 A
# 70 – 80 B
# 60 – 70 C
# 50 – 60 D
# 0 – 50 E
# i) Write a program with a function called get_grade which accepts the
# average mark and returns the grade to the main function which then outputs it.                                  (8
# marks)

# ii) Write a program with a function called grade which accepts the average mark and prints the grade.


def get_marks():
    subj1 = int(input("Enter mark for subject 1: "))
    subj2 = int(input("Enter marks for subject 2: "))
    subj3 = int(input("Enter marks for subject 3; "))

    sum = subj1 + subj2 + subj3
    return sum


def get_grade(sum):
    average = sum / 3

    if 80 < average <= 100:
        return "A"
    elif 70 < average <= 80:
        return "B"
    elif 60 < average <= 70:
        return "C"
    elif 50 < average <= 60:
        return "D"
    elif 0 <= average <= 50:
        return "E"

    return average


marks = get_marks()
grade = get_grade(marks)

print(f"The average is {round(marks / 3)} You have attained a {grade}")
