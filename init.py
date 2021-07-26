import yaml
import globals

def init():
    print("init")

    objects_yaml_file = open("resources/objects.yaml")
    globals.object_file =  yaml.full_load(objects_yaml_file)

    map_yaml_file = open("resources/map.yaml")
    globals.map_file = yaml.full_load(map_yaml_file)
    return