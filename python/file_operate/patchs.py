import sys
import os
from os import path
import string
PATCH_SUFFIX = ".patch"
rootList = os.listdir("./")
TAG = "patchs"
arg =  sys.argv[1]
if arg == "0":
    value = 'enable="1"'
    value_want = 'enable="0"'
elif arg == "1":
    value = 'enable="0"'
    value_want = 'enable="1"'
else:
    print "arg enter error."
for suitList in rootList:
    if suitList.startswith(TEST_SUIT_TAG) and path.isdir(suitList):
        childList = os.listdir("./"+suitList+"/")
        for list in childList:
            if list.startswith(suitList + ".xml"):
                p = "./" + suitList + "/"+ list
                try:
                    lines = open(p).readlines()
                    s = ""
                    for line in lines:
                        if value in line:
                            s += line.replace(value, value_want)
                            print(list + " have changed.")
                        else:
                            s += line
                    f = open(p,'w')
                    f.write(s)
                    f.close()
                except IOError:
                    print(TAG, 'open file error')