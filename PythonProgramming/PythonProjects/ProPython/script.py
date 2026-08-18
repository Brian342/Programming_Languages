import pandas as pd
import pypyodbc

# Read Excel file
excel_file_path = 'covid_Vacinations.xls'
df = pd.read_excel(excel_file_path)

# Display the first few rows and data types
print(df.head())
print(df.columns)
print(df.dtypes)


# Database connection details
server = 'localhost'
database = 'Portfolio_project'
username = 'SA'
password = '<YourStrong@Passw0rd>'

# Connection string
conn_str = f'DRIVER={{ODBC Driver 17 for SQL Server}};SERVER={server};DATABASE={database};UID={username};PWD={password}'

# Establish connection
conn = pypyodbc.connect(conn_str)
cursor = conn.cursor()

# Create table based on DataFrame structure
# Adjust column names and data types as per your DataFrame
create_table_query = """
CREATE TABLE PortfolioExcel (
    iso_code NVARCHAR(50),
    continent NVARCHAR(50),
    location NVARCHAR(100),
    date DATE

);
"""
cursor.execute(create_table_query)
conn.commit()

# Insert data into SQL Server
for index, row in df.iterrows():
    cursor.execute("""
    INSERT INTO PortfolioExcel (
        iso_code, continent, location, date
    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """,
    row['iso_code'], row['continent'], row['location'], row['date'])

# Commit and close connection
conn.commit()
cursor.close()
conn.close()

print("Data imported successfully!")