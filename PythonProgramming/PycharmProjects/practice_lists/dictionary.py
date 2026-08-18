# dictionary = a changeable, unordered collection of unique key:
#              value pairs. they are fast because they use hashing,
#              which allows us to access a value quickly.

capitals = {'usa': ' washington Dc',
            "India": 'New deli',
            'kenya': 'Nairobi',
            'Russia': 'Moscow'}
capitals.update({"Germany": "Berlin"})
capitals.update({"kenya": "Juja"})
capitals.pop("kenya")
capitals.clear()

# print(capitals.get("kenya"))
# print(capitals.keys())
# print(capitals.values())
# print(capitals.items())

for key,value in capitals.items():
    print(key, value)