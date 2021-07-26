import yaml
import globals
import ruamel.yaml
from pathlib import Path
from pprint import pprint

def init():

    objects_yaml_file = open("resources/objects.yaml")
    globals.object_file =  yaml.full_load(objects_yaml_file)

    map_yaml_file = open("resources/map.yaml")
    globals.map_file = yaml.full_load(map_yaml_file)

    input = Path('resources/map.yaml')
    tyaml = ruamel.yaml.YAML()
    data = tyaml.load(input)
    pprint(data)

    del data[0]['name']
    pprint(data)
    return