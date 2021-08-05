import init
import game_play


def ex(zin):
    print(">>> "+zin)
    game_play.parse_user_input(zin)
    print(" ")


print("MQ Adventure")
init.init()

# The start
ex('look')
ex('pick paperclip')
ex('read scroll')
ex('go north')
ex('use paperclip on door')
ex('go north')
ex('read plate')

# go to the queen
ex('go north')
ex('go east')
ex('go north')
ex('go east')
ex('go north')
ex('go east')
ex('go north')

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
