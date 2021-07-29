import init
import game_play


def ex(zin):
    print(">>> "+zin)
    game_play.execute(zin)
    print(" ")

print("MQ Adventure")
init.init()
# ERRORS AND INVALID THINGS
ex('   look    ')
ex('rgfekrgcn &*^*&^(* %(*&^% 9875 (8759& h8tthcie7rgc 7344             egcergcercer')
ex('   look    north    ')
ex('   look    door    ')
ex('adgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgdsadgdgdsfgsdgdsgdgdgdsfgds')

# LOOK TESTS
ex('look')
ex('look paperclip')
ex('look skull')
ex('look old skull')
ex('read scroll')

# PICK AND DROP TESTS
ex('inventory')
ex('pick skull')
ex('look')
ex('inventory')
ex('drop skull')
ex('inventory')
ex('look')

# COMMUNICATE TEST
ex('talk old man')
ex('pick skull')
ex('give skull')
ex('give skull to')
ex('give skull to bastard')
ex('give skull to old man')
ex('inventory')

# MOVE TEST
ex('go east')
ex('go north')
ex('pick paperclip')
ex('use bottle on door')
ex('use paperclip on door')
ex('go north')
ex('look plate')
ex('read plate')
