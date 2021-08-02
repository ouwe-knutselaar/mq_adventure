

class matrix:

    __matrix = []

    def __init__(self, x, y):
        x = x + 1
        y = y + 1
        self.__matrix = [[0 for w in range(x)] for h in range(y)]
        print(self.__matrix)

    def set(self, x, y, val):
        self.__matrix[x][y] = val
        print(self.__matrix)
