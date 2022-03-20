package mqadventure.actions;

import mqadventure.CallBackFunctions;
import mqadventure.data.MoveableObjects;

import java.util.List;
import java.util.Optional;

public class DropObject extends Callback implements Action {
    public DropObject(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> objectsToDropNameList) {
        objectsToDropNameList.forEach(this::dropObject);

    }

    private void dropObject(String objectToDropName){
        Optional<MoveableObjects> objectToDropOpt = callBackFunctions.
                getInventory().
                stream().
                filter(objectToDrop -> objectToDrop.getName().equals(objectToDropName)).
                findFirst();
        objectToDropOpt.
                ifPresent(objectToDrop -> {callBackFunctions.
                        getCurrentRoom().
                        getMoveable_objects().
                        add(objectToDrop);
                        callBackFunctions.toOutput("You dropped "+objectToDropName);
                });
        callBackFunctions.
                getInventory().
                removeIf(object -> object.getName().equals(objectToDropName));
    }
}
