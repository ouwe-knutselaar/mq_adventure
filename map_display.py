import init
import globals
from objects.adv_matrix import matrix


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

init.init()

max_name_len=0
min_x = 0
min_y = 0
max_x = 0
max_y = 0

room_array = []

def get_room_by_name(name):
    for room in globals.map_file:
        if room['name'] == name:
            return room


def add_object(obj, x, y):

    for room in room_array:
        if room.get_name() == obj['name']:
            return
    print('add room ' + obj['name'] + 'x=' + str(x) + ' y=' + str(y))
    t_room= room_class(obj['name'],x,y)
    room_array.append(t_room)

    for junction in obj['junctions']:
        # print("junction "+ junction)
        j_room = get_room_by_name(obj['junctions'][junction]['goes_to'])
        if junction == 'north':
            add_object(j_room, x, y+1)
        if junction == 'south':
            add_object(j_room, x, y-1)
        if junction == 'east':
            add_object(j_room, x+1, y)
        if junction == 'west':
            add_object(j_room, x-1, y)


for obj in globals.map_file:
    # print(str(obj['name']))
    if len(obj['name']) > max_name_len:
        max_name_len = len(obj['name'])

for obj in globals.map_file:
    if obj['name'] == 'startingpoint':
        add_object(obj,0,0)



print("max name len = "+str(max_name_len))

for room in room_array:
    print(str(room))
    if room.get_x() < min_x:
        min_x = room.get_x()
    if room.get_x() > max_x:
        max_x = room.get_x()
    if room.get_y() < min_y:
        min_y = room.get_y()
    if room.get_y() > max_y:
        max_y = room.get_y()

print("max_x = " + str(max_x))
print("min_x = " + str(min_x))
print("max_y = " + str(max_y))
print("min_y = " + str(min_y))


map = matrix(max_x,max_y)

for room in room_array:
    map.set(room.get_x(), room.get_y(), 1)


