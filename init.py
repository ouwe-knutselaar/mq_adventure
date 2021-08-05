import globals
import ruamel.yaml
from pathlib import Path

map_yaml_file = Path("resources/map.yaml")

def __validity_check_empty_fields(keyword):
    for spaces in globals.map_file:
        if keyword in spaces:
            if spaces[keyword] == None:
                print("map file error")
                print(spaces)
                print("The part:"+keyword+" is empty")
                exit(1)


def init():
    print("init map file")
    ruamel_yml_obj = ruamel.yaml.YAML()

    map_yaml_file = Path("resources/map.yaml")
    globals.map_file = ruamel_yml_obj.load(map_yaml_file)
    __validity_check_empty_fields('persons')
    __validity_check_empty_fields('moveable_objects')
    __validity_check_empty_fields('look')
    __validity_check_empty_fields('junctions')
    return

def save():
    print("save map file")
    with open(map_yaml_file, 'w', encoding='utf-8') as f:
        ruamel.yaml.dump(globals.map_file, f, Dumper=ruamel.yaml.RoundTripDumper)