# def import_child(module_name):
#     module = __import__(module_name)
#     for layer in module_name.split(' . ')[1:]:
#         module = getattr(module, layer)
#     return module
#
#
# print(import_child('os.path'))
#
# print(import_child('os'))

# import sys
#
#
# def import_child(module_name):
#     __import__(module_name)
#     return sys.modules[module_name]
#
#
# print(import_child('os.path'))

# print(import_child('os'))

from importlib import import_module
print(import_module('os.path'))