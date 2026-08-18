# # import matplotlib.pyplot as plt
# #
# # categories = ['A', 'B', 'C', 'D']
# # values = [25, 40, 30, 20]
# #
# # plt.bar(categories, values)
# # plt.title('Bar Chart')
# # plt.xlabel('Categories')
# # plt.ylabel('Values')
# # plt.show()
# import matplotlib.pyplot as plt
# import numpy as np
#
# labels = np.array(['A', 'B', 'C', 'D', 'E'])
# data = np.array([4, 5, 3, 4, 2])
#
# angles = np.linspace(0, 2 * np.pi, len(labels), endpoint=False)
# data = np.concatenate((data, [data[0]]))
# angles = np.concatenate((angles, [angles[0]]))
#
# plt.polar(angles, data, marker='o')
# plt.fill(angles, data, alpha=0.25)
# plt.title('Radar chart')
# plt.show()
# import matplotlib.pyplot as plt
# import seaborn as sns
# import numpy as np
#
# data = np.random.rand(10, 10)
#
# sns.heatmap(data, annot=True)
# plt.title('heatmap')
# plt.show()
# import matplotlib.pyplot as plt
#
# x = [1, 2, 3, 4, 5]
# y1 = [10, 15, 25, 30, 35]
# y2 = [5, 10, 20, 25, 30]
#
# plt.fill_between(x, y1, y2, color='skyblue', alpha=0.4)
# plt.title('Area chart')
# plt.xlabel('x-axis')
# plt.ylabel('y-axis')
# plt.show()
import subprocess

