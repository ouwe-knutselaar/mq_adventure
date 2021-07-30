import init
import game_play


def ex(zin):
    print(">>> "+zin)
    game_play.execute(zin)
    print(" ")

print("MQ Adventure")
init.init()

ex('look')
ex('pick paperclip')
ex('read scroll')
ex('go north')
ex('use paperclip on door')
ex('go north')
ex('read plate')

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