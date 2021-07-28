import init
import game_play


def voer_uit(zin):
    print(">>> "+zin)
    game_play.execute(zin)
    print(" ")

print("MQ Adventure")
init.init()

voer_uit('look')
voer_uit('look paperclip')
voer_uit('look skull')
voer_uit('read scroll')
voer_uit('inventory')
voer_uit('pick skull')
voer_uit('inventory')
voer_uit('look')

voer_uit('talk old man')
voer_uit('give gray skull to old man')