import globals
import ruamel.yaml
from pathlib import Path


def init():
    ruamel_yml_obj = ruamel.yaml.YAML()

    map_yaml_file = Path("resources/map.yaml")
    globals.map_file = ruamel_yml_obj.load(map_yaml_file)


    return