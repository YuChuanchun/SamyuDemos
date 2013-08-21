import e
o = None

class O(object):
    def __init__(self):
        print "init"
    def p(self):
        print "print me"
def init():
    global o
    o = O()
init()