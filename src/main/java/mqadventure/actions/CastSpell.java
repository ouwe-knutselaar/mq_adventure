package mqadventure.actions;

import mqadventure.CallBackFunctions;
import mqadventure.StringTools;
import mqadventure.data.Room;

import java.util.List;

public class CastSpell extends Callback implements Action{

    public CastSpell(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> parameters) {
        if(parameters.size() == 1){
            callBackFunctions.toOutput("What???");
            return;
        }
        String castedSpell = StringTools.stringArrayToString(parameters,1);

        Room currentRoom=callBackFunctions.getCurrentRoom();

        if(  ! (currentRoom.getSpell().hasObjectNamed(castedSpell) |
                currentRoom.getJunctions().stream().filter(junction -> junction.getSpell().equals(castedSpell)).findFirst().isPresent() |
                currentRoom.getMonster().stream().filter(monster -> monster.getDamaging_spells().contains(castedSpell)).findFirst().isPresent())
        ){
            callBackFunctions.toOutput(castedSpell+" is not a spell");
            return;
        }

        currentRoom.getSpell().stream().
                filter(spell -> spell.getName().equals(castedSpell)).
                forEach(spell -> callBackFunctions.toOutput(spell.getResponse()));

        currentRoom.getJunctions().stream().
                filter(junction -> junction.getSpell().equals(castedSpell)).
                forEach(junction -> junction.setBlocked("no"));

        currentRoom.getMonster().forEach(monster -> callBackFunctions.toOutput(monster.castSpellToMonster(castedSpell)) );
    }
}
