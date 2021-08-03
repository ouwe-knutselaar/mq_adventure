import re
import init
from objects import map_display
import objects.view_object as view_object




def design_loop():
    current_object = "startingpoint"
    loop = True

    while loop:
        user_input = input( current_object+" > ")
        user_input = re.sub("\s\s+", " ", user_input)
        user_input_array = user_input.split(" ")
        if user_input_array[0] =='map':
            map_display.print_map()
        if user_input_array[0] == 'view':
            view_object.view_object(user_input_array[1])
            current_object = user_input_array[1]





def main():
    print("MQ Adventure Designer")
    init.init()
    design_loop()

if __name__ == "__main__":
    main()