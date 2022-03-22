package mqadventure.actions;

import mqadventure.CallBackFunctions;
import mqadventure.StringTools;
import mqadventure.data.Room;

import java.util.List;

public class TalkToObject extends Callback implements Action{

    public TalkToObject(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> parameters) {
        Room currentRoom = callBackFunctions.getCurrentRoom();
        if(parameters.size() == 1){
            callBackFunctions.toOutput("Say to who??");
            return;
        }
        String personToTalkTo = StringTools.stringArrayToString(parameters,1);

        if(currentRoom.getPersons().missingObjectNamed(personToTalkTo)){
            callBackFunctions.toOutput("There is no "+personToTalkTo);
            return;
        }

        currentRoom.getPersons().stream().
                filter(persons -> persons.getName().equals(personToTalkTo)).
                peek(persons -> callBackFunctions.toOutput(persons.getTalk())).
                forEach(persons -> persons.getReceive().forEach(object -> callBackFunctions.getInventory().add(object)));

    }
}
