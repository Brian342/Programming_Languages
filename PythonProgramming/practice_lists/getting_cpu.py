import psutil
cpu_count =psutil.cpu_count()
cpu_percent = psutil.cpu_percent(interval=1)

print("cpu count: ", cpu_count)
print("cpu_percentage: ", cpu_percent)