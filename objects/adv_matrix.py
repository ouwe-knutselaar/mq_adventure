

class Matrix:

    __matrix = {}
    __max_x = 0
    __max_y = 0
    __min_x = 0
    __min_y = 0
    __max_val_len = 0

    def __setmax(self,x,y,value):
        if x > self.__max_x:
            self.__max_x = x
        if x < self.__min_x:
            self.__min_x = x
        if y > self.__max_y:
            self.__max_y = y
        if y < self.__min_y :
            self.__min_y = y
        if len(value) > self.__max_val_len:
            self.__max_val_len = len(value)

    def add(self, x, y, value):
        if (x,y) in self.__matrix:
            return False
        self.__matrix[(x,y)] = value
        self.__setmax(x ,y, value)
        return True

    def update(self,x,y,value):
        self.__matrix[(x, y)] = value
        self.__setmax(x, y, value)

    def get(self,x,y):
        if (x, y) in self.__matrix:
            return self.__matrix[(x,y)]+ " " * (self.__max_val_len - len(self.__matrix[(x,y)]))
        return " "*self.__max_val_len

    def dump(self):
        print("range " + str(self.__min_x) + ": " + str(self.__max_x) + ": " + str(self.__min_y) + ": " + str(self.__max_y))

        for y in range(self.__min_y,self.__max_y+1):
            for x in range(self.__min_x, self.__max_x + 1):
                print("+" + "-" * (2 + self.__max_val_len),end='')
            print("+")
            for x in range(self.__min_x, self.__max_x + 1):
                # print("" + str(self.get(x,y) ) + "("+str(x) + "," + str(y) + ") ",end='')
                print("| "+ str(self.get(x, y)) + " ", end='')
            print("|")
        for x in range(self.__min_x, self.__max_x + 1):
            print("+" + "-" * (2 + self.__max_val_len), end='')
        print("+")
