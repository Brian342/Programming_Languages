import os
import shutil  # this delete folders that contain information on it

path = "folder"

try:
    # os.remove(path) # this removes files only
    # os.rmdir(path)  # removes an empty directory.
    shutil.rmtree(path)  # removes a folder that contain a file inside it.
except FileNotFoundError:
    print("That folder was not found")
except PermissionError:
    print("You dont have permission to delete that!!")
except OSError:
    print("you can not delete that using that function!!")
else:
    print(path + " was deleted successfully")
