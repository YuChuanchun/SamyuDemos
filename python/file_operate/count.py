import sys

TAG = "count"
count = 0
arg = sys.argv[1]

try:
    file = open("file.txt")
except IOError:
    print("open file.txt failed.")

lines = file.readlines()

for line in lines:
    if line.startswith(arg):
        count += 1
print(TAG, "count:" + str(count))