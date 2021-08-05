import globals
import re
import init

def __user_input(msg):
    user_input = input(msg + " > ")
    user_input = re.sub("\s\s+", " ", user_input)
    return user_input

def __room_exists(name):
    for room in globals.map_file:
        if room['name'] == name:
            return True
    return False


def __get_room(name):
    for room in globals.map_file:
        if room['name'] == name:
            return room
    return None


def delete_room(room_name):
    if not __room_exists(room_name):
        print(room_name + " does not exists")
        return

    index = 0
    for rooms in globals.map_file:
        if rooms['name'] == room_name:
            globals.map_file.pop(index)
            break
        index += 1
    print("room " + room_name + "deleted")

    for rooms in globals.map_file:
        if 'junctions' not in rooms:
            continue
        junctions = rooms['junctions']
        if 'north' in junctions and junctions['north']['goes_to'] == room_name:
            rooms['junctions'].pop('north')
        if 'east' in junctions and junctions['east']['goes_to'] == room_name:
            rooms['junctions'].pop('east')
        if 'south' in junctions and junctions['south']['goes_to'] == room_name:
            rooms['junctions'].pop('south')
        if 'west' in junctions and junctions['west']['goes_to'] == room_name:
            rooms['junctions'].pop('west')
    init.save()
    init.init()


def __add_junction(direction,room_name,this_room):
    if not __room_exists(room_name):
        print(room_name + " does not exists")
        return

    opposite_tnl={'north':'south','east':'west','south':'north','west':'east'}
    opposite = opposite_tnl[direction]

    room = __get_room(room_name)
    if opposite in room['junctions']:
        print(room['name']+ " has already a connection in this direction")

    this_room['junctions']={}
    this_room['junctions'][direction]={}
    this_room['junctions'][direction]['goes_to']=room_name
    globals.map_file.append(this_room)

    if 'junctions' not in room:
        room['junctions'] = {}
    room['junctions'][opposite] = {}
    room['junctions'][opposite]['goes_to']=this_room['name']

    print('from room' + this_room['name'] + ' created a junction ' + direction + " to "+ room_name)
    print('from room' + room_name + 'created a junction ' + opposite + " " + this_room['name'])


def add_room(name):
    print("add room " + name)
    if __room_exists(name):
        print("A room with name " + name + " already exists")
        return
    description = __user_input("give room description")
    room_dict={}
    room_dict['name'] = name
    room_dict['description'] = description
    print("new room "+str(room_dict))
    __edit_menu(room_dict)
    init.save()
    init.init()


def __edit_menu(room):
    loop = True

    while loop:
        print("edit room")
        print("n e s w <name>: set junction")
        print("l             : set look or view")
        print("m             : set movable object")
        print("p             : set person")
        print("q             : quit and save")
        user_input = __user_input("choice").split(" ")
        if  user_input[0] == 'q':
            loop = False

        if len(user_input) < 2:
            print("error")
            continue
        if user_input[0] == 'n':
            __add_junction('north',user_input[1],room)
        if user_input[0] == 'e':
            __add_junction('east',user_input[1],room)
        if user_input[0] == 's':
            __add_junction('south',user_input[1],room)
        if user_input[0] == 'w':
            __add_junction('west',user_input[1],room)








