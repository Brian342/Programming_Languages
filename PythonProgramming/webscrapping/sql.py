# import mysql.connector
import pandas as pd
from sqlalchemy import create_engine
import os


# set up the database connection
username = "root"
password = ""
host = "localhost"  # or your server IP, e.g., "192.168.1.100"
port = "3306"       # default MySQL port
database_name = "databasesoccer"
engine = create_engine(f'mysql+mysqlconnector://{username}:{password}@{host}:{port}/{database_name}')

# file a path
csv_file = '/Users/briankimanzi/Documents/Excel/netflix1.csv'
# Dynamically set the table name based on the file name
table_name = os.path.splitext(os.path.basename(csv_file))[0]
# load csv file
try:
    data = pd.read_csv(csv_file, encoding='utf-8')
except UnicodeDecodeError:
    print('Encoding error encountered, Retrying with ISO-8859-1 encoding.')
    data = pd.read_csv(csv_file, encoding='ISO-8859-1')

# write data to sql, using the dynamic table name
data.to_sql(table_name, engine, if_exists='append', index=False)

print(f"Data from '{csv_file}' has been successfully imported into the table '{table_name}' in the database!")


'Mysql@localhost:3306'