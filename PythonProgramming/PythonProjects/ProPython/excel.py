import pandas as pd

# read Excel file
excel_file_path = '/Users/briankimanzi/Documents/Excel/covid_deaths.xls'
df = pd.read_excel(excel_file_path)

# Display the first few rows and data types to understand the structure
print(df.head())
print(df.dtypes)

