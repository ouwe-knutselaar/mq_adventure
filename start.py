import globals
import player
import re


loop = True

def get_location(location_name):
    for objs in globals.map_file:
        if objs['name'] == location_name:
            return objs

def get_look(location,look_to):
    type = location['type']
    if 'look' in location:
        if look_to in location['look']:
            return location['look'][look_to]

    if look_to in globals.object_file['location'][type]['look']:
        return globals.object_file['location'][type]['look'][look_to]

    return globals.object_file['location'][type]['look']['default']


def get_description(location):
    description=location['description']+"\n"
    for obj in location['fixed_object']:
        description += obj['description']+"\n"
    for obj in location['moveable_objects']:
        description += obj['description']+"\n"
    return description

def pick_object(location, object_name):
    for obj in location['moveable_objects']:
        if obj['name'] == object_name:
            print(dir(obj))
            print(obj.__iter__())
            print("you picked up the "+object_name)
            player.inventory.append(obj)
            del location['moveable_objects'][obj]


def get_inventory():
    print("you have:")
    for object in player.inventory:
        print("- "+str(object['name']))

def start():
    while loop:
        location = get_location(player.current_place)
        print(get_description(location))
        user_input = input(">>> ")
        user_input = re.sub("\s\s+", " ", user_input)
        #print(user_input)
        user_input_array = user_input.split(" ")
        user_input_array.append("nill")
        user_input_array.append("nill")
        user_input_array.append("nill")
        user_input_array.append("nill")
        if user_input_array[0] == "look":
            print(get_look(location,user_input_array[1]))
            continue
        if user_input_array[0] == "pick":
            print(pick_object(location,user_input_array[1]))
            continue
        if user_input_array[0] == "inventory":
            print(get_inventory())
            continue
        print("what?")

    return