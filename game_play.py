import globals
import player
import re


loop = True

def get_location(location_name):
    for objs in globals.map_file:
        if objs['name'] == location_name:
            return objs

def get_record_in_group_by_name(location,group,name):
    if group not in location:
        return False, None
    for item in location[group]:
        if item['name'] == name:
            return True,item
    return False,None

def get_look(location,look_to):
    if look_to == 'nill':
        print(get_description(location))
        return
    if look_to in location['look']:
        print(location['look'][look_to])
        return

    have_it,item = get_record_in_group_by_name(location,'moveable_objects',look_to)
    if not have_it:
        print("I see no " + look_to)
        return
    if 'look' in item:
        print(item['look'])
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
    arraypos = 0;
    for obj in location['moveable_objects']:
        if obj['name'] == object_name:
            print("you picked up the " + object_name)
            player.put_in_inventory(obj)
            del location['moveable_objects'][arraypos]
            return
        arraypos = arraypos +1
    print("there is no " + object_name + " to pick up")

def drop_object(location,object_name):
    if 'moveable_objects' not in location:
        location.insert(0,key='moveable_objects',value=[])

    if player.has_item(object_name):
        have_it,item = player.get_from_inventory(object_name)
        location['moveable_objects'].append(item)
        return
    print("You don't have a "+object_name)

def get_inventory():
    print("you have:")
    if len(player.inventory) == 0:
        print("Nothing")
        return
    for object in player.inventory:
        print("- "+str(object['name']))


def go_direction(location, direction):
    if direction == 'nill':
        print("You want to go to nowhere?")
        return

    if direction not in location['junctions']:
        print("you cannot go " + direction)
        return

    if 'blocked' in location['junctions'][direction] and location['junctions'][direction]['blocked'] == 'yes':
        print("Sorry, the " + location['junctions'][direction]['name'] + " is closed")
        return

    player.current_place = location['junctions'][direction]['goes_to']
    if 'description' in location['junctions'][direction]:
        print(location['junctions'][direction]['description'])
    location = get_location(player.current_place)
    print(get_description(location))


def use_object_on(location, t_array):
    t_array.pop(0)

    if 'on' not in t_array:
        print("Use on what?")
        return

    object_array = []
    for item in t_array:
        if item == 'on':
            break
        object_array.append(item)
    object_to_use = ' '.join(map(str, object_array))

    on_index = t_array.index('on') + 1
    aplay_action_to = ' '.join(map(str, t_array[on_index:]))

    have_it, item  = player.get_from_inventory(object_to_use)
    if not have_it:
        print("You don't have a " + object_to_use)
        return

    for junction in location['junctions']:
        if location['junctions'][junction]['name'] == aplay_action_to and location['junctions'][junction]['unblock'] == object_to_use:
            location['junctions'][junction]['blocked'] = 'no'
            print("You unlocked the " + aplay_action_to)
            return
    print("that didn't work")

def give_object_to_person(location,t_array):
    t_array.pop(0)

    if 'to' not in t_array:
        print("Give to who?")
        return

    on_index = t_array.index('to')
    object_to_give = ' '.join(map(str, t_array[:on_index]))
    if not player.has_item(object_to_give):
        print("you don't have a "+object_to_give)
        return

    on_index = t_array.index('to') + 1
    person_name = ' '.join(map(str, t_array[on_index:]))

    if 'persons' not in location:
        print("nobody to give "+object_to_give+" to")
        return

    have_it, person = get_record_in_group_by_name(location, 'persons', person_name)
    if not have_it:
        print("There is no '"+person_name+"'")
        return

    if 'give' not in person:
        print("The "+person_name+" does not wants the "+object_to_give)
        return
    if object_to_give != person['give']['object']:
        print("The " + person_name + " does not wants the " + object_to_give)
        return

    print(person['give']['response'])
    player.get_from_inventory(object_to_give)

    if 'receive' not in person['give']:
        return

    for item in person['give']['receive']:
        player.put_in_inventory(item)
        print("The "+person_name+" gives you a "+item['name'])
    person['give']['receive'] = []



def read_object(location,object_to_read):
    have_it,item = get_record_in_group_by_name(location,'moveable_objects',object_to_read)
    if have_it:
        print(item['read'])
        return
    if 'read' in location:
        if object_to_read in location['read']:
            print(location['read'][object_to_read])
            return
    print("Nothing to read on "+object_to_read)


def open_object(location, object_to_open):
    have_it, item_to_open = get_record_in_group_by_name(location, 'moveable_objects', object_to_open)
    if not have_it:
        print("There is no " + object_to_open + " to open")
        return
    if 'open' not in item_to_open:
        print(object_to_open + " will not open")
        return
    for item in item_to_open['open']:
        player.put_in_inventory(item)
        print("You have a:"+item['description'])
    item_to_open['open'] = []


def talk_person(location, person_to_talk):
    for item in location['persons']:
        if person_to_talk == item['name']:
            try:
                print(item['talk'])
            except:
                print("Nothing to read on " + person_to_talk)

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
talk ...      try to start a conversation
open ...      open something
"""
    )

def concat_array(t_array):
    t_array.pop(0)
    return ' '.join(map(str, t_array))

def execute(user_input):
    location = get_location(player.current_place)
    #print(user_input)
    user_input = user_input.strip()
    user_input=re.sub('\s+', ' ', user_input)
    if len(user_input) > 256:
        print("Did is hurt to write "+str(len(user_input))+" letters? Get to the point!")
        return

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

    if  user_input_array[0] == "open":
        open_object(location,concat_array(user_input_array))
        return

    if user_input_array[0] == "help":
        get_help()
        return

    print("what?")

    return