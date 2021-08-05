import re
import init
from objects import map_display
import objects.view_object as view_object
import objects.edit


def design_loop():
    current_object = "startingpoint"
    loop = True

    while loop:
        user_input = input(current_object+" > ")
        user_input = re.sub("\s\s+", " ", user_input)
        user_input_array = user_input.split(" ")
        if user_input_array[0] == 'map':
            map_display.print_map()
            continue

        if len(user_input_array) < 2:
            print("command needs 1 argument")
            continue
        if user_input_array[0] == 'view':
            view_object.view_object(user_input_array[1])
            current_object = user_input_array[1]
            continue
        if user_input_array[0] == 'add':
            objects.edit.add_room(user_input_array[1])
            current_object = user_input_array[1]
            continue
        if user_input_array[0] == 'del':
            objects.edit.delete_room(user_input_array[1])
            current_object = user_input_array[1]
            continue
        if user_input_array[0] == 'edit':
            objects.edit.edit_room(user_input_array[1])
            current_object = user_input_array[1]
            continue

        print("unknown command")


def main():
    print("MQ Adventure Designer")
    init.init()
    design_loop()


if __name__ == "__main__":
    main()
