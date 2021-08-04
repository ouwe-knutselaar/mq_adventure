import init
import globals
from objects.adv_matrix import Matrix


class room_class:
    __name = ""
    __width = 0
    __height = 0
    __pos_x = 0
    __pos_y = 0

    def __init__(self, name, x, y):
        self.__name = name
        self.__pos_x = x
        self.__pos_y = y

    def __str__(self) -> str:
        return self.__name + " " + str(self.__pos_x) + " " + str(self.__pos_y)

    def get_name(self):
        return self.__name

    def get_x(self):
        return self.__pos_x

    def get_y(self):
        return self.__pos_y

#min_x = 0
#min_y = 0
#max_x = 0
#max_y = 0
room_array = []


def get_room_by_name(name):
    for room in globals.map_file:
        if room['name'] == name:
            return room


def add_object(room_obj, x, y):

    for room in room_array:
        if room.get_name()['name'] == room_obj['name']:
            return
    print('add room ' + room_obj['name'] + ' x=' + str(x) + ' y=' + str(y))
    t_room= room_class(room_obj, x, y)
    room_array.append(t_room)

    for junction in room_obj['junctions']:
        # print("junction "+ junction)
        j_room = get_room_by_name(room_obj['junctions'][junction]['goes_to'])
        if junction == 'north':
            print(" - north")
            add_object(j_room, x, y-1)
        if junction == 'south':
            print(" - south")
            add_object(j_room, x, y+1)
        if junction == 'east':
            print(" - east")
            add_object(j_room, x+1, y)
        if junction == 'west':
            print(" - west")
            add_object(j_room, x-1, y)


def print_map():
    max_name_len = 0
    for obj in globals.map_file:
        # print(str(obj['name']))
        if len(obj['name']) > max_name_len:
            max_name_len = len(obj['name'])

    for obj in globals.map_file:
        if obj['name'] == 'startingpoint':
            add_object(obj,0,0)

    print("max name len = "+str(max_name_len))
    map = Matrix()
    for room in room_array:
        map.update(room.get_x(), room.get_y(), room.get_name())
    map.dump()


