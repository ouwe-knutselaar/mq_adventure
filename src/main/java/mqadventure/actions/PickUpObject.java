package mqadventure.actions;

import mqadventure.CallBackFunctions;
import mqadventure.data.MoveableObjects;

import java.util.List;
import java.util.Optional;

public class PickUpObject extends Callback implements Action{


    public PickUpObject(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> objectNamesToPickUpList) {
        objectNamesToPickUpList.forEach(this::pickUp);
    }


    private void pickUp(String objectToPickUpName){
        Optional<MoveableObjects> objectToPickUpOpt = callBackFunctions.
                getCurrentRoom().
                getMoveable_objects().
                stream().
                filter(objectToPickUp -> objectToPickUp.
                        getName().
                        equals(objectToPickUpName)).
                findFirst();
        objectToPickUpOpt.
                ifPresent(objectToPickUp -> {callBackFunctions.getInventory().add(objectToPickUp);
                                             callBackFunctions.toOutput("You picked up "+objectToPickUpName+"\n");}
                                              );
        callBackFunctions.
                getCurrentRoom().
                getMoveable_objects().
                removeIf(pickedUpObject -> pickedUpObject.getName().equals(objectToPickUpName));
    }
}
