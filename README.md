# patientco

Exercise
Using any language of your choice, write a CSV file to JSON converter. Use any tools or libraries that help,
with the exception of those designed to handle CSV parsing.
The CSV file will consist of multiple rows. The rows are newline-delimited and columns are comma-separated.
The first row will always be a header row. All rows will have the same number of columns.
Entries can optionally be enclosed in double quotation marks, with quotation marks in the data denoted as ""
inside of a quoted field (For example """This is a great idea,"" he said."). Any ascii characters, including
newlines, can appear inside of quotations.
The data will be transformed into a JSON output that consists of an array of objects. Each object will be
composed of fields with the names of the headers and values from the corresponding row. Numbers should
translate to JSON numbers while anything else is a string.
The CSV file can be comma separated or pipe delimited
For example:
ID, Name, Age, Favorite Color
1, Bob, 23, Red
2, "Doe, John", 99, """Turquoise"""
Or
ID| Name| Age| Favorite Color
1| Bob| 23| Red
2| "Doe, John"| 99| """Turquoise"""
Will translate to:
[{"ID": 1,
"Name": "Bob",
"Age": 23,
"Favorite Color": "Red"},
{"ID": 2,
"Name": "Doe, John",
"Age": 99,
"Favorite Color": "\"Turquoise\""}]
Don't worry about making the JSON format neatly with whitespace. All that matters is that it is valid. Assume
that the JSON will be consumed by a third party REST API.
Extra points for the following feature:
Build a simple UI (whatever you prefer Javascript, VueJs, Angular) where you can upload a CSV file and allow
the user to choose the type of delimiter( a comma or a pipe ) and after the parsing shows an option to
download the JSON file. (Download should just download the file in the filesystem, not anything outside of that,
the file should not live in a remote location)
