import globals
import player
import re
import os


loop = True
debug = False


def __has_item_named(location, stanza, name):
    if stanza not in location:
        return False, None
    for item in location[stanza]:
        if item['name'] == name:
            return True, item
    return False, None

def get_location(location_name):
    for objs in globals.map_file:
        if objs['name'] == location_name:
            return objs


def get_record_in_group_by_name(location, group, name):
    if group not in location:
        return False, None
    for item in location[group]:
        if item['name'] == name:
            return True, item
    return False, None


def get_look(location, look_to):
    if look_to == 'nill':
        return get_description(location)
    has_it, item = __has_item_named(location,'look',look_to)
    if has_it:
        return item['description']

    has_it, item = __has_item_named(location, 'moveable_objects', look_to)
    if has_it:
        return item['description']
    return 'I see no ' + look_to


def get_description(location):
    if debug:
        return "loc_name=" + location['name']
    description = location['description'] + "\n"
    if 'persons' in location:
        for obj in location['persons']:
            description += obj['description']+"\n"
    if 'moveable_objects' in location:
        for obj in location['moveable_objects']:
            description += obj['description']+"\n"
    return description


def pick_object(location, object_name):
    arraypos = 0
    for obj in location['moveable_objects']:
        if obj['name'] == object_name:
            player.put_in_inventory(obj)
            del location['moveable_objects'][arraypos]
            return "you picked up the " + object_name
        arraypos = arraypos + 1
    return "there is no " + object_name + " to pick up"


def drop_object(location, object_name):
    if 'moveable_objects' not in location:
        location.insert(0, key='moveable_objects', value=[])

    if player.has_item(object_name):
        have_it, item = player.get_from_inventory(object_name)
        location['moveable_objects'].append(item)
        return
    return "You don't have a " + object_name


def get_inventory():
    print("you have:")
    if len(player.inventory) == 0:
        return "You have Nothing"
        return
    inventory_list=""
    for item in player.inventory:
        inventory_list += "- "+str(item['name'])+ "\n"
    return  inventory_list


def go_direction(location, direction):
    if direction == 'nill':
        return "You want to go to nowhere?"

    dir_item = None
    for item in location['junctions']:
        if direction == item['name']:
            dir_item = item
    if dir_item == None:
        return "You cannot go "+direction

    if 'blocked' in dir_item and dir_item['blocked'] == 'yes':
        return "Sorry, the " + item['object_name'] + " is closed"

    player.current_place = dir_item['goes_to']
    return dir_item['description'] + "\n" + get_description(get_location(player.current_place))


def use_object_on(location, t_array):
    t_array.pop(0)
    if 'on' not in t_array:
        return "Use on what?"
    object_array = []
    for item in t_array:
        if item == 'on':
            break
        object_array.append(item)
    object_to_use = ' '.join(map(str, object_array))
    on_index = t_array.index('on') + 1
    aplay_action_to = ' '.join(map(str, t_array[on_index:]))

    have_it, item = player.get_from_inventory(object_to_use)
    if not have_it:
        return "You don't have a " + object_to_use

    for junction in location['junctions']:
        if 'object_name' in junction and junction['object_name'] == aplay_action_to and 'unblock' in junction and junction['unblock'] == object_to_use:
            junction['blocked'] = 'no'
            return "You unlocked the " + aplay_action_to
    return "that didn't work"


def give_object_to_person(location, t_array):
    t_array.pop(0)
    if 'to' not in t_array:
        return "Give to who?"
    on_index = t_array.index('to')
    object_to_give = ' '.join(map(str, t_array[:on_index]))
    if not player.has_item(object_to_give):
        return "you don't have a " + object_to_give
    on_index = t_array.index('to') + 1
    person_name = ' '.join(map(str, t_array[on_index:]))

    if 'persons' not in location:
        return "nobody to give "+object_to_give+" to"

    has_it, person = __has_item_named(location, 'persons', person_name)
    if not has_it:
        return "There is no '" + person_name + "'"

    if 'give' not in person:
        return "The " + person_name+" does not wants the " + object_to_give
    if object_to_give != person['give']['object']:
        return "The " + person_name + " does not wants the " + object_to_give

    has_it, item = player.get_from_inventory(object_to_give)

    if 'receive' in person['give']:
        for item in person['give']['receive']:
            player.put_in_inventory(item)
            person['give']['receive'] = []
            return "The "+person_name+" gives you a "+item['name']
    return person['give']['response']

def read_object(location, object_to_read):
    #have_it, item = get_record_in_group_by_name(location, 'moveable_objects', object_to_read)
    has_it, item =__has_item_named(location,'moveable_objects',object_to_read)
    if has_it and 'read' in item:
        return item['read']
    has_it, item = __has_item_named(location, 'read', object_to_read)
    if has_it:
        return item['description']
    return "Nothing to read on "+object_to_read


def open_object(location, object_to_open):
    have_it, item_to_open = get_record_in_group_by_name(location, 'moveable_objects', object_to_open)
    if not have_it:
        return "There is no " + object_to_open + " to open"
    if 'open' not in item_to_open:
        return object_to_open + " will not open"
    for item in item_to_open['open']:
        player.put_in_inventory(item)
        return "You have a:"+item['description']
    item_to_open['open'] = []


def talk_person(location, person_to_talk):
    if 'persons' not in location:
        return "There is no " + person_to_talk

    for person in location['persons']:
        if person['name'] == person_to_talk:
            return person['talk']
    return "There is no " + person_to_talk

def cast_spell(location, spell):
    has_it,item=__has_item_named(location,'cast',spell)
    if not has_it:
        return "That did.....   absolutely nothing!"
    return item['response']



def get_help():
    return """
look .....    look around, direction or object
pick .....    pick up an object
drop ....     drop an object
inventory     see what you got
go ....       go direction
use ... on .. try to use 
read ...      read something
talk ...      try to start a conversation
open ...      open something
cast ....     cast a spell
"""


def concat_array(t_array):
    t_array.pop(0)
    return ' '.join(map(str, t_array))


def screen_clear():
    if os.name == 'posix':
        os.system('clear')
    else:
        os.system('cls')


def parse_user_input(user_input):
    location = get_location(player.current_place)
    print(location['name'])

    user_input_array = user_input.split(" ")
    if len(user_input_array) == 1:
        user_input_array.append("nill")

    if user_input_array[0] == "look":
        return get_look(location,concat_array(user_input_array))

    if user_input_array[0] == "pick":
        return pick_object(location,concat_array(user_input_array))

    if user_input_array[0] == "drop":
        return drop_object(location, concat_array(user_input_array))

    if user_input_array[0] == "inventory":
        return get_inventory()

    if user_input_array[0] == "go":
        return go_direction(location, concat_array(user_input_array))

    if user_input_array[0] == "use":
        return use_object_on(location, user_input_array)

    if user_input_array[0] == "give":
        return give_object_to_person(location, user_input_array)

    if user_input_array[0] == "read":
        return read_object(location, concat_array(user_input_array))

    if user_input_array[0] == "talk":
        return talk_person(location, concat_array(user_input_array))

    if user_input_array[0] == "open":
        return open_object(location, concat_array(user_input_array))

    if user_input_array[0] == "cast":
        return cast_spell(location, concat_array(user_input_array))

    if user_input_array[0] == "help":
        return get_help()

    return "what?"


def execute():
    screen_clear()
    print("MQ Adventure")
    while loop:
        # print(get_description(get_location(player.current_place)))
        user_input = input(">>> ")
        user_input = re.sub("\s\s+", " ", user_input)
        user_input = user_input.strip()
        user_input=re.sub('\s+', ' ', user_input)
        if len(user_input) > 256:
            print("Did is hurt to write "+str(len(user_input))+" letters? Get to the point!")
            continue
        if user_input == 'exit':
            print("Exit the MQ adventure")
            return
        screen_clear()
        print(parse_user_input(user_input))
