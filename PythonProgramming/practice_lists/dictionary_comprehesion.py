# dictionary comprehension = create dictionaries using an expression
#                            can replace for loops and certain lambda functions

#  dictionary = {key: expression for (key,value) in iterable)
#  dictionary = {key: expression for (key,value) in iterable if condition)
#  dictionary = {key: (if/else) for (key,value) in iterable}
#  dictionary = {key: function(value) for (key,value) in iterable}
# ---------------------------------------------------------------------------------------------
# cities_in_F = {'New york': 32, 'Boston': 75, 'Los Angeles': 100, 'Chicago': 50}
# cities_in_C = {key: round((value - 32) * (5/9)) for (key, value) in cities_in_F.items()}
# output = print
# output(cities_in_C)
# ---------------------------------------------------------------------------------------------
# weather = {'New York': "snowing", 'Boston': "sunny", 'Los Angeles': "sunny", 'Chicago': "cloudy"}
# sunny_weather = {key: value for (key, value) in weather.items() if value == "sunny"}
# print(sunny_weather)
# ---------------------------------------------------------------------------------------------
# cities = {'New york': 32, 'Boston': 75, 'Los Angeles': 100, 'Chicago': 50}
# desc_cities = {key: ("WARM" if value >= 40 else "cold") for (key,value) in cities.items()}
# print(desc_cities)
# ---------------------------------------------------------------------------------------------
def check_temp(value):
    if value >= 70:
        return "Hot"
    elif 69 >= value >= 40:
        return "WARM"
    else:
        return "COLD"


cities = {'New york': 32, 'Boston': 75, 'Los Angeles': 100, 'Chicago': 50}
desc_cities = {key: check_temp(value) for (key, value) in cities.items()}
print(desc_cities)
