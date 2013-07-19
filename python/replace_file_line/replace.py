import sys
f = open("file.txt", 'r')
lines = f.readlines()
newLines = ''
boo = False
value =  sys.argv[1]
type = "TOTAL_CASE"
for line in lines:
     if line.find(type) >= 0:
          print line
          line = type + ':' + str(value) + '\n'
          boo = True
     newLines += line
f.close()

f = open("file.txt", 'w')
if not boo:
    f.write(type + ':' + str(value) + '\n')
f.write(newLines)
f.close()
