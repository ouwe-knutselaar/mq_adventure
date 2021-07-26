import yaml
import globals
import ruamel.yaml
from pathlib import Path
from pprint import pprint

def init():
    ruamel_yml_obj = ruamel.yaml.YAML()

    objects_yaml_file = Path("resources/objects.yaml")
    globals.object_file = ruamel_yml_obj.load(objects_yaml_file)

    map_yaml_file = Path("resources/map.yaml")
    globals.map_file = ruamel_yml_obj.load(map_yaml_file)


    return