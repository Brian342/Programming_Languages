# **********************************************
# python MultiProcessing
# *********************************************
# MultiProcessing = running tasks in parallel on different cpu cores, bypasses GIL used for threading
#                   MultiProcessing = better for cpu bound tasks (heavy cpu usage)
#                   multithreading = better for io bound tasks (waiting around)

from multiprocessing import process, cpu_count
import time


def counter(num):
    count = 0
    while count < num:
        count += 1


def main():
    print(cpu_count())
    a = process(target=counter, args=(1000000000,))
    a.start()

    a.join()

    print("Finished in:", time.perf_counter(), "seconds")


if __name__ == '__main__':
    main()
