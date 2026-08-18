class Animal:

    def eat(self):  # method signature combination of both the method
        # name and its parameters
        print("This animal is eating")


class Rabbit(Animal):

    def eat(self):
        print("This rabbit is eating a carrot")


rabbit = Rabbit()
rabbit.eat()
