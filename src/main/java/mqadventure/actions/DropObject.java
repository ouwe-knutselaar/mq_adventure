package mqadventure.actions;

import mqadventure.CallBackFunctions;
import mqadventure.StringTools;
import mqadventure.data.MoveableObjects;

import java.util.List;
import java.util.Optional;

public class DropObject extends Callback implements Action {
    public DropObject(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> objectsToDropNameList) {
        if(objectsToDropNameList.size()==1){
            callBackFunctions.toOutput("Drop what?");
            return;
        }

        dropObject(StringTools.stringArrayToString(objectsToDropNameList,1));
    }

    private void dropObject(String objectToDropName){
        if(!callBackFunctions.getInventory().stream().filter(object -> object.getName().equals(objectToDropName)).findFirst().isPresent()){
            callBackFunctions.toOutput("You do not have a "+objectToDropName);
            return;
        }
        MoveableObjects objectToDrop = callBackFunctions.getInventory().stream().filter(object -> object.getName().equals(objectToDropName)).findFirst().get();
        callBackFunctions.getCurrentRoom().getMoveable_objects().add(objectToDrop);
        callBackFunctions.getInventory().removeIf(object -> object.getName().equals(objectToDropName));
    }
}
