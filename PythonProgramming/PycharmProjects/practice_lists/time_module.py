# epoch = a date and time from which a computer measures system time
# UTC = coordinated universal time or UTC is the primary time standard
#       by which the world regulates clock and time.
#       it is within about 1 second of mean solar time at 0 degree longitude,
#       and is not adjusted for daylight saving time
import time

#  print(time.ctime(1000000))  # convert a time expresses in seconds since epoch to a readable string
#                       epoch = when your computer thinks time began (reference point)

# print(time.time())  # return current seconds since epoch

# print(time.ctime(time.time()))  # prints the current time
#
# time_object = time.localtime()
# print(time_object)
# local_time = time.strftime("%B %d %Y %H:%M:%S", time_object)
# print(local_time)
#                                           % B locale's full month name
#                                           % d day of the month as a decimal number
#                                           % y year with century as a decimal number
#                                           % H hour (24-hour clock) as a decimal[00,23]
#                                           % M minute as a decimal number[00,59]
#                                           % S second as a decimal number[00,61]
#                                          (nb) check the official python documentation
# time_object = time.gmtime() # UTC time
# print(time_object)

# time_string = "20 April, 2020"
# time_object = time.strptime(time_string, "%d %B, %Y")
# print(time_object )

# (year, month, day, hours, minutes, secs, # day of the week, #day of the year, dst)
time_tuple = (2024, 1, 22, 1, 27, 30, 0, 22, 0)
time_string = time.asctime(time_tuple)
print(time_string)

# (year, month, day, hours, minutes, secs, # day of the week, #day of the year, dst)
time_tuple = (2024, 1, 22, 1, 27, 30, 0, 22, 0)
time_string = time.mktime(time_tuple)  # mktime tell how many secs have been since epoch
print(time_string)
