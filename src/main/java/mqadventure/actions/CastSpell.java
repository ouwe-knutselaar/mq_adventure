package mqadventure.actions;

import mqadventure.CallBackFunctions;
import mqadventure.StringTools;

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

        callBackFunctions.
                getCurrentRoom().getSpell().stream().
                filter(spell -> spell.getName().equals(castedSpell)).
                forEach(response -> callBackFunctions.toOutput(response.getResponse()));

        callBackFunctions.
                getCurrentRoom().
                getJunctions().stream().
                filter(junction -> junction.getSpell().equals(castedSpell)).
                forEach(junction -> junction.setBlocked("no"));

        callBackFunctions.getCurrentRoom().getMonster().forEach(monster -> callBackFunctions.toOutput(monster.castSpellToMonster(castedSpell)) );
    }
}
