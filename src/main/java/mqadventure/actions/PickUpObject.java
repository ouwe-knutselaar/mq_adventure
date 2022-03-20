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
        if(objectNamesToPickUpList.size()==1){
            callBackFunctions.toOutput("Pick up what?");
            return;
        }
        objectNamesToPickUpList.remove(0);
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
        if(objectToPickUpOpt.isPresent()){
            callBackFunctions.getInventory().add(objectToPickUpOpt.get());
            callBackFunctions.toOutput("You picked up a "+objectToPickUpName);
        }
        else{
            callBackFunctions.toOutput("There is no "+objectToPickUpName);
        }


        callBackFunctions.
                getCurrentRoom().
                getMoveable_objects().
                removeIf(pickedUpObject -> pickedUpObject.getName().equals(objectToPickUpName));


    }
}
