package mqadventure.actions;

import mqadventure.CallBackFunctions;
import mqadventure.StringTools;
import mqadventure.data.Room;
import java.util.List;

public class PickUpObject extends Callback implements Action{


    public PickUpObject(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> objectNamesToPickUpList) {
        if(objectNamesToPickUpList.size()==1){
            callBackFunctions.toOutput("Pick up what?");
            return;
        }
        pickUp(StringTools.stringArrayToString(objectNamesToPickUpList,1));
    }


    private void pickUp(String objectToPickUpName){
        Room currentRoom = callBackFunctions.getCurrentRoom();

        if(currentRoom.getMoveable_objects().missingObjectNamed(objectToPickUpName)){
            callBackFunctions.toOutput("There is no "+objectToPickUpName+" to pick up");
            return;
        }

        callBackFunctions.getInventory().add(currentRoom.getMoveable_objects().getElementWithName(objectToPickUpName));
        currentRoom.getMoveable_objects().removeIf(pickedUpObject -> pickedUpObject.getName().equals(objectToPickUpName));
        callBackFunctions.toOutput("You picked up a "+objectToPickUpName);
    }


}
