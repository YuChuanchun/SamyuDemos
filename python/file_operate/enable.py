import xml.etree.ElementTree as ET
import sys
import os
from os import path
import string
TEST_SUIT_TAG = 'test_suit_'
rootList = os.listdir("./")
TAG = "enable"
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
    elif suitList.startswith(TEST_SUIT_TAG) and suitList.endswith(".xml"):
        try:
            case_lines = open(suitList).readlines()
            case_s = ""
            for case_line in case_lines:
                if value in case_line:
                    case_s += case_line.replace(value, value_want)
                    print(suitList + " have changed.")
                else:
                    case_s += case_line
            case_f = open(suitList,'w')
            case_f.write(case_s)
            case_f.close()
        except IOError:
            print(TAG, 'open file error')
            