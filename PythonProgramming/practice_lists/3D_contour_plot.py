import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

# create a meshgrid of x and y values
x = np.linspace(-5, 5, 100)
y = np.linspace(-5, 5, 100)
x, y = np.meshgrid(x, y)


# define a function to calculate the z values (height) based on x and y
def f(x, y):
    return np.sin(np.sqrt(x ** 2 + y ** 2))


# calculate the z values for the meshgrid
z = f(x, y)

# create a three-dimensional contour plot
fig = plt.figure(figsize=(8, 6))
ax = fig.add_subplot(111, projection='3d')
contour = ax.contour3D(x, y, z, 50, cmap='viridis')

# add labels and color bar
ax.set_xlabel('X-axis')
ax.set_ylabel('Y-axis')
ax.set_zlabel('Z-axis')
fig.colorbar(contour, ax=ax, label='Z values')

# show the plot
plt.show()

