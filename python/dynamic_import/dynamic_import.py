c = __import__("a.b.c", fromlist=["a.b"])
print("c:", c)
c.p()

b = __import__("b", globals(), locals(), [], -1)
print b
b.p()

print b.list