import globals
import ruamel

indent_size = 2

def view_object(name):
    for room in globals.map_file:
        if room['name'] == name:
            objprint(room, 0)




def objprint(room, indent):
    indent_str = str(indent) + " " + " " * indent
    #print(indent_str + str(type(room)))
    #print(indent_str + str(room))

    for item in room:
        if type(room[item]) == str:
            print(indent_str + str(item) + ": " +str(room[item]))
            continue

        if isinstance(room[item],str):
            print(indent_str + str(item) + "| " +str(room[item]))
            continue

        if isinstance(room[item],ruamel.yaml.comments.CommentedSeq):
            print(indent_str + str(item)+ ': ')
            for seq in room[item]:
                objprint(seq, indent + indent_size)
            continue

        if isinstance(room[item], ruamel.yaml.comments.CommentedMap):
            print(indent_str + str(item)+ ': ')
            objprint(room[item],indent + indent_size)
            continue



