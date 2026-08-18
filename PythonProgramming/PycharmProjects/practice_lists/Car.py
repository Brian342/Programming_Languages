# Attributes = describe what an object is/has
class Car:

    wheels = 4  # class variable (Declared  within a class but outside the constructor)

    def __init__(self, make, model, year, color):  # constructor
        self.make = make    # instance variable (it is declared inside a constructor and given a unique value )
        self.model = model  # instance variable
        self.year = year    # instance variable
        self.color = color  # instance variable

    # def drive(self):
    #     print("This " + self.model + " is driving")
    #
    # def stop(self):
    #     print("This " + self.model + " is stopped")
