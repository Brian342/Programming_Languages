# *****************************************************
# if__name__=='__main__'
# *******************************************************

# 1. module can be run as a standalone program
# 2. module can be imported and used by other modules

# python interpreter sets "special variables", one of which is __name__
# then python will execute the code found withing __main__

# if __name__ == '__main__':
#     print("running this module directly")
# else:
#     print("running other module indirectly")
def main():
    print("Hello")


if __name__ == '__main__':
    main()