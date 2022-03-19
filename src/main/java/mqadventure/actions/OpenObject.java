package mqadventure.actions;

import mqadventure.CallBackFunctions;
import mqadventure.StringTools;
import mqadventure.data.MoveableObjects;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OpenObject extends Callback implements Action{

    public OpenObject(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> objectToOpenList) {
        objectToOpen(StringTools.stringArrayToString(objectToOpenList,1));
    }

    private void objectToOpen(String objectToOpenName){
        Optional<MoveableObjects> objectToOpen = callBackFunctions.
                getCurrentRoom().
                getMoveable_objects().stream().
                filter(moveableObject -> moveableObject.getName().equals(objectToOpenName)).findFirst();
        objectToOpen.ifPresent(openedObject -> {
                    callBackFunctions.getInventory().add(openedObject.getOpen());
                    callBackFunctions.toOutput("You find a " + openedObject.getOpen().getDescription() + "\n");
                });
        callBackFunctions.
                getCurrentRoom().
                getMoveable_objects().
                removeIf(pickedUpObject -> pickedUpObject.getName().equals(objectToOpenName));


        }
}
