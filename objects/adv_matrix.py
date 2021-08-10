

class Matrix:

    __matrix = {}
    __max_x = 0
    __max_y = 0
    __min_x = 0
    __min_y = 0
    __max_val_len = 0

    def __init__(self):
        self.__matrix={}

    def __setmax(self,x,y,name):
        if x > self.__max_x:
            self.__max_x = x
        if x < self.__min_x:
            self.__min_x = x
        if y > self.__max_y:
            self.__max_y = y
        if y < self.__min_y :
            self.__min_y = y
        if len(name) > self.__max_val_len:
            self.__max_val_len = len(name)

    def add(self, x, y, value):
        if (x,y) in self.__matrix:
            return False
        self.__matrix[(x,y)] = value
        self.__setmax(x ,y, value['name'])
        return True

    def update(self, x, y, value):
        self.__matrix[(x, y)] = value
        self.__setmax(x, y, value['name'])

    def get_obj_name(self, x, y):
        if (x, y) in self.__matrix:
            name = self.__matrix[(x, y)]['name']
            return name + " " * (self.__max_val_len - len(name))
        return " " * self.__max_val_len

    def get_obj_junctions(self, x, y):
        dirstr = ""
        if (x, y) in self.__matrix:
            for junctions in self.__matrix[(x, y)]['junctions']:
                if 'north' == junctions['name']:
                    dirstr = dirstr + "N"
                if 'east' == junctions['name']:
                    dirstr = dirstr + "E"
                if 'south' == junctions['name']:
                    dirstr = dirstr + "S"
                if 'west' == junctions['name']:
                    dirstr = dirstr + "W"
        return dirstr+ " " * (self.__max_val_len - len(dirstr))

    def get_obj_objects(self, x, y):
        if (x, y) in self.__matrix:
            if 'moveable_objects' in self.__matrix[(x,y)]:
                moveable_objects = len(self.__matrix[(x,y)]['moveable_objects'])
                objstr = "OBJ:" + str(moveable_objects)
                return objstr + " " * (self.__max_val_len - len(objstr))
        return " "*self.__max_val_len

    def get_obj_persons(self, x, y):
        if (x, y) in self.__matrix:
            if 'persons' in self.__matrix[(x,y)]:
                persons = len(self.__matrix[(x,y)]['persons'])
                objstr = "PRS:" + str(persons)
                return objstr + " " * (self.__max_val_len - len(objstr))
        return " "*self.__max_val_len

    # Dump the whole map of the adventure
    def dump(self):
        print("range " + str(self.__min_x) + ": " + str(self.__max_x) + ": " + str(self.__min_y) + ": " + str(self.__max_y))

        print("y\\x\t  ",end='')
        for x in range(self.__min_x, self.__max_x + 1):

            print(str(x) + " " * (2+ self.__max_val_len), end='')
        print("")
        for y in range(self.__min_y,self.__max_y+1):

            # print top line
            print("\t", end='')
            for x in range(self.__min_x, self.__max_x + 1):
                print("+" + "-" * (2 + self.__max_val_len),end='')
            print("+")

            # print names
            print(str(y) + "\t",end='')
            for x in range(self.__min_x, self.__max_x + 1):
                print("| " + str(self.get_obj_name(x, y)) + " ", end='')
            print("|")

            # print directions
            print("\t", end='')
            for x in range(self.__min_x, self.__max_x + 1):
                print("| " + str(self.get_obj_junctions(x, y)) + " ", end='')
            print("|")

            # print number of objects
            print("\t", end='')
            for x in range(self.__min_x, self.__max_x + 1):
                print("| " + str(self.get_obj_objects(x, y)) + " ", end='')
            print("|")

            # print persons
            print("\t", end='')
            for x in range(self.__min_x, self.__max_x + 1):
                print("| " + str(self.get_obj_persons(x, y)) + " ", end='')
            print("|")

        print("\t", end='')

        # print bottom line
        for x in range(self.__min_x, self.__max_x + 1):
            print("+" + "-" * (2 + self.__max_val_len), end='')
        print("+")
