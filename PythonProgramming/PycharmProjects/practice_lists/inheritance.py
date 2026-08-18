# inheritance =
class Animal:
    alive = True

    def eat(self):
        print("The animal is eating")

    def sleep(self):
        print("The animal is sleeping")


class Rabbit(Animal):
    def run(self):
        print("The rabbit is running")


class Fish(Animal):
    def swim(self):
        print("This fish is swimming")


class Hawk(Animal):
    def fly(self):
        print("This hawk is flying")


rabbit = Rabbit()  # object
fish = Fish()
hawk = Hawk()


# print(rabbit.alive)
# fish.eat()
# hawk.sleep()
rabbit.run()
fish.swim()
hawk.fly()
