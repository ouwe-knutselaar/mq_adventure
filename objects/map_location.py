
class Map_Location:

    __posx = 0
    __posy = 0
    __name = ""

    def __init__(self, x, y, name):
        self.__name = name
        self.__posx = x
        self.__posy = y

    def get_location(self):
        return (self.__posx,self.__posx)

    def get_name(self):
        return self.__name