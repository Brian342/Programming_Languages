import logging


def count_lines(sample):
    """Count the number of lines in a file. if the file can't be
    opened, it should be treated the same as it was empty."""
    file = None
    try:
        file = open(sample, 'r')
        lines = file.readlines()
    except TypeError as e:
        print("The filename wasn't valid for use with the filesystem")
        logging.error(e)
        return 0
    except EnvironmentError as e:
        logging.error(e.args[1])
        return 0
    except UnicodeDecodeError as e:
        logging.error(e)
        return 0
    else:
        return len(lines)
    finally:
        if file:
            file.close()
            "this is a comment"


print(count_lines("sample"))
# def validate(value, validator):
#     try:
#         return validator(value)
#     except Exception as e:
#         raise ValueError('invalid value: %s' % value) from e
#
#
# def validator(value):
#     if len(value) > 10:
#         raise ValueError("value can't exceed 10 characters")
#
#
# validate('test', validator)
# try:
#     validate(False, validator)
# except Exception as e:
#     print(type(e))



