import globals
import player
import re


loop = True

def get_location(location_name):
    for objs in globals.map_file:
        if objs['name'] == location_name:
            return objs


def get_record_in_group_by_name(location,group,name):
    for item in location[group]:
        if item['name'] == name:
            return item
    return None

def get_look(location,look_to):
    if look_to == 'nill':
        print(get_description(location))
        return
    if look_to in location['look']:
        print(location['look'][look_to])
        return

    item = get_record_in_group_by_name(location,'moveable_objects',look_to)
    if item != None:
        if 'actions' in item:
            if 'look' in item['actions']:
                print(item['actions']['look'])
        else:
            print("you see just a "+look_to)
        return
    print('I see no '+look_to)

def get_description(location):
    description=location['description']+"\n"
    if 'persons' in location:
        for obj in location['persons']:
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
        print("You want to go to nowhere?")
        return
    try:
        if 'blocked' in location['junctions'][direction] and location['junctions'][direction]['blocked'] == 'yes':
            print("Sorry, the "+location['junctions'][direction]['name']+" is closed")
            return

        player.current_place = location['junctions'][direction]['goes_to']
        print(location['junctions'][direction]['description'])
        location = get_location(player.current_place)
        print(get_description(location))
    except Exception as e:
        print(e)
        print("Sorry, you cannot go "+direction)

def use_object_on(location,t_array):
    t_array.pop(0)
    object_array=[]
    for item in t_array:
        if item == 'on':
            break
        object_array.append(item)
    object_to_use = ' '.join(map(str, object_array))

    on_index = t_array.index('on') + 1
    aplay_action_to = ' '.join(map(str, t_array[on_index:]))

    have_it = False
    for items in player.inventory:
        if object_to_use == items['name']:
            have_it = True
    if not have_it:
        print("You don't have a "+ object_to_use)
        return

    for junction in location['junctions']:
        if location['junctions'][junction]['name'] == aplay_action_to and location['junctions'][junction]['unblock'] == object_to_use:
            location['junctions'][junction]['blocked'] = 'no'
            print("You unlocked the "+ aplay_action_to)
            return
    print("that didn't work")

def give_object_to_person(location,t_array):
    t_array.pop(0)

    on_index = t_array.index('to')
    object_to_give = ' '.join(map(str, t_array[:on_index]))

    on_index = t_array.index('to') + 1
    person_name = ' '.join(map(str, t_array[on_index:]))

    if 'persons' in location:
        for person in location['persons']:
            if person['name'] == person_name and 'give' in person:
                print(person['give']['response'])


def read_object(location,object_to_read):
    for item in location['moveable_objects']:
        if object_to_read == item['name']:
            try:
                print(item['read'])
            except:
                print("Nothing to read on "+object_to_read)

def talk_person(location,person_to_talk):
    for item in location['persons']:
        if person_to_talk == item['name']:
            try:
                print(item['talk'])
            except:
                print("Nothing to read on "+person_to_talk)

def get_help():
    print(
"""
look .....    look around, direction or object
pick .....    pick up an object
drop ....     drop an object
inventory     see what you got
go ....       go direction
use ... on .. try to use 
read ...      read something
"""
    )

def concat_array(t_array):
    t_array.pop(0)
    return ' '.join(map(str, t_array))

def execute(user_input):
    location = get_location(player.current_place)
    #print(user_input)
    user_input_array = user_input.split(" ")
    if len(user_input_array) == 1:
        user_input_array.append("nill")

    if user_input_array[0] == "look":
        get_look(location,concat_array(user_input_array))
        return

    if user_input_array[0] == "pick":
        pick_object(location,concat_array(user_input_array))
        return

    if user_input_array[0] == "drop":
        drop_object(location, concat_array(user_input_array))
        return

    if user_input_array[0] == "inventory":
        get_inventory()
        return

    if user_input_array[0] == "go":
        go_direction(location, concat_array(user_input_array))
        return

    if  user_input_array[0] == "use":
        use_object_on(location,user_input_array)
        return

    if  user_input_array[0] == "give":
        give_object_to_person(location,user_input_array)
        return

    if  user_input_array[0] == "read":
        read_object(location,concat_array(user_input_array))
        return

    if  user_input_array[0] == "talk":
        talk_person(location,concat_array(user_input_array))
        return

    if user_input_array[0] == "help":
        get_help()
        return

    print("what?")

    return