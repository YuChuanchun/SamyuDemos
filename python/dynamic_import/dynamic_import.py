c = __import__("a.b.c", fromlist=["a.b"])
print("c:", c)
c.p()