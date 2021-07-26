import yaml
import globals
import ruamel.yaml
from pathlib import Path
from pprint import pprint

input = Path('resources/map.yaml')
tyaml = ruamel.yaml.YAML()
data = tyaml.load(input)
print(dir(data))
pprint(data)
print(len(data))

print("\n")
for item in data:
    print(dir(item))
    pprint(item,indent=2)
    move_objects=item['moveable_objects']
    print(len(move_objects))
    print("\n")



del data[0]
pprint(data)
print(len(data))