import globals
import player
import re


loop = True

def get_location(location_name):
    for objs in globals.map_file:
        if objs['name'] == location_name:
            return objs

def get_look(location,look_to):
    if look_to == 'nill':
        print(get_description(location))
        return
    if 'look' in location:
        if look_to in location['look']:
            print(location['look'][look_to])
            return
    for obj in location['moveable_objects']:
        if look_to == obj['name']:
            try:
                print(obj['actions']['look'])
            except:
                print("you see just a "+look_to)
            return
    try:
        print(globals.object_file['location'][location['type']]['look'][look_to])
    except:
        print(globals.object_file['location'][location['type']]['look']['default'])



def get_description(location):
    description=location['description']+"\n"
    if 'fixed_objects' in location:
        for obj in location['fixed_objects']:
            description += obj['description']+"\n"
    if 'moveable_objects' in location:
        for obj in location['moveable_objects']:
            description += obj['description']+"\n"
    return description


def pick_object(location, object_name):
    arraypos=0;
    for obj in location['moveable_objects']:
        if obj['name'] == object_name:
            print("you picked up the "+object_name)
            player.inventory.append(obj)
            del location['moveable_objects'][arraypos]
            return
        arraypos = arraypos +1
    print("there is no "+object_name+" to pick up")


def drop_object(location,object_name):
    if 'moveable_objects' not in location:
        location.insert(0,key='moveable_objects',value=[])
    arraypos = 0;
    for obj in player.inventory:
        if obj['name'] == object_name:
            location['moveable_objects'].append(obj)
            del player.inventory[arraypos]
            print("you dropped the "+object_name)
            return
        arraypos = arraypos +1
    print("you don't have a "+object_name)


def get_inventory():
    print("you have:")
    for object in player.inventory:
        print("- "+str(object['name']))


def go_direction(location,direction):
    if direction == 'nill':
        print("You want to go to "+direction+"? Well, you cannot go there")
        return
    try:
        player.current_place = location['junctions'][direction]['goes_to']
        location = get_location(player.current_place)
        print(get_description(location))
    except:
        print("Sorry, you cannot go "+direction)

def get_help():
    print(
"""
look .....    look around, direction or object
pick .....    pick up an object
drop ....     drop an object
inventory     see what you got
go ....       go direction
"""
    )

def start():
    while loop:
        location = get_location(player.current_place)
        user_input = input(">>> ")
        user_input = re.sub("\s\s+", " ", user_input)
        #print(user_input)
        user_input_array = user_input.split(" ")
        user_input_array.append("nill")
        user_input_array.append("nill")
        user_input_array.append("nill")
        user_input_array.append("nill")
        if user_input_array[0] == "look":
            get_look(location,user_input_array[1])
            continue
        if user_input_array[0] == "pick":
            pick_object(location,user_input_array[1])
            continue
        if user_input_array[0] == "drop":
            drop_object(location, user_input_array[1])
            continue
        if user_input_array[0] == "inventory":
            get_inventory()
            continue
        if user_input_array[0] == "go":
            go_direction(location, user_input_array[1])
            continue
        if user_input_array[0] == "help":
            get_help()
            continue
        print("what?")

    return