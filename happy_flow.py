import init
import game_play


def ex(zin):
    print(">>> "+zin)
    print(game_play.parse_user_input(zin))
    print(" ")


print("MQ Adventure")
init.init()

# The start
ex('look')
ex('pick paperclip')
ex('read scroll')
ex('go east')
ex('use paperclip on door')
ex('go east')
ex('read plate')

# go to the queen
ex('go north')
ex('go east')
ex('go north')
ex('go east')
ex('go north')
ex('go east')

ex('look queen')
ex('talk queen')

ex('go west')
ex('go west')
ex('go west')

ex('open old basket')
ex('inventory')

ex('go east')
ex('go east')
ex('go east')

ex('give puppy to queen')
ex('inventory')

# go the the Island master
ex('go west')
ex('go west')
ex('go west')
ex('go south')
ex('go south')
ex('go south')
ex('go east')
ex('use incident ticket on golden door')
ex('go east')
ex('go east')
ex('look')
ex('talk master')
ex('go south')
ex('go west')
ex('read plate')
ex('go south')
ex('pick paper')
ex('go east')
ex('pick creditcard')
ex('go east')
ex('pick badge')
ex('go north')
ex('go west')
ex('go north')
ex('give paper to master')
ex('give creditcard to master')
ex('give badge to master')
ex('inventory')
ex('use code on door')
ex('go east')

# into the coal mines
ex('go east')
ex('go south')
ex('go south')
ex('talk bald man')
ex('pick flashlight')
ex('go east')
ex('go east')
ex('go north')
ex('pick transistor')
ex('go south')
ex('go west')
ex('go west')
ex('give transistor to bald man')

# Go to the motor temple
ex('go south')
ex('go south')
ex('go east')
ex('go east')
ex('talk monk')
ex('cast sudo su - root')