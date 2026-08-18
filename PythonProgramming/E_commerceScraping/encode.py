# with open('/Users/briankimanzi/Documents/Excel/netflix1.csv', 'r', encoding='utf-8', errors='ignore') as infile:
#     content = infile.read()
#
# with open('/Users/briankimanzi/Documents/Excel/netflix1_encoding.csv', 'w', encoding='utf-8', errors='ignore') as outfile:
#     outfile.write(content)

input_file =  "/Users/briankimanzi/Documents/Excel/netflix1.csv"
output_file = "/Users/briankimanzi/Documents/Excel/netflix1_utf8.csv"


# Read the file with the correct encoding and errors set to 'ignore' to avoid crashing on bad characters
with open(input_file, "r", encoding="ISO-8859-1", errors="ignore") as infile:
    content = infile.read()

# Write it out as UTF-8
with open(output_file, "w", encoding="utf-8") as outfile:
    outfile.write(content)
