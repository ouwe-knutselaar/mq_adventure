package mqadventure.actions;

import mqadventure.CallBackFunctions;
import mqadventure.StringTools;

import java.util.List;

public class TalkToObject extends Callback implements Action{

    public TalkToObject(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> parameters) {
        if(parameters.size() == 1){
            callBackFunctions.toOutput("Say to who??");
            return;
        }
        String personToTalkTo = StringTools.stringArrayToString(parameters,1);

        if(!callBackFunctions.getCurrentRoom().getPersons().stream().filter(persons -> persons.getName().equals(personToTalkTo)).findFirst().isPresent()){
            callBackFunctions.toOutput("There is no "+personToTalkTo);
            return;
        }

        callBackFunctions.
                getCurrentRoom().getPersons().stream().
                filter(persons -> persons.getName().equals(personToTalkTo)).
                forEach(persons -> callBackFunctions.toOutput(persons.getTalk()));
        callBackFunctions.
                getCurrentRoom().getPersons().stream().
                filter(persons -> persons.getName().equals(personToTalkTo)).
                forEach(persons -> persons.getReceive().forEach(obejct -> callBackFunctions.getInventory().add(obejct)));
    }
}
