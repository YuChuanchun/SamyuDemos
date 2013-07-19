def p():
    print 1
    if False:
        print 2
        return True
boo = p()
if boo == None:
    print True
else:
    print boo