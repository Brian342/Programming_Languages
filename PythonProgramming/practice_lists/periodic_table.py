import periodictable

Atomic_no = int(input("Enter Element Atomic No: "))
element = periodictable.elements[Atomic_no]
print("Atomic Number: ", element.number)
print("Symbol: ", element.symbol)
print("Name: ", element.name)
print("Atomic mass: ", element.mass)
print("Density: ", element.density)
