import init
import game_play
import re

loop = True

def game_loop():
    while loop:
        user_input = input(">>> ")
        user_input = re.sub("\s\s+", " ", user_input)
        game_play.execute(user_input)

def main():
    print("MQ Adventure")
    init.init()
    game_loop()

if __name__ == "__main__":
    main()