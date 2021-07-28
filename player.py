
name = "player"
current_place = "startingpoint"
direction = "north"

inventory = []

def has_item(name):
    for item in inventory:
        if name == item['name']:
            return True
    return False

def get_from_inventory(name):
    array_pos=0;
    for item in inventory:
        if name == item['name']:
            inventory.pop(array_pos)
            return True,item
        array_pos +=1
    return False,""

def put_in_inventory(name):
    inventory.append(name)


