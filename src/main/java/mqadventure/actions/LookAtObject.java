package mqadventure.actions;

import mqadventure.CallBackFunctions;
import java.util.List;

public class LookAtObject extends Callback implements Action {


    public LookAtObject(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> objectToLookAtList) {
        if(objectToLookAtList.size()==1)callBackFunctions.showRoom();
        objectToLookAtList.forEach(this::lookAt);
    }

    private void lookAt(String objectToLookAt){
        callBackFunctions.
                getCurrentRoom().getLook().stream().
                filter(look -> look.getName().equals(objectToLookAt)).
                forEach(look -> callBackFunctions.toOutput(look.getDescription()));
        callBackFunctions.
                getCurrentRoom().getMoveable_objects().stream().
                filter(moveableObjects -> moveableObjects.getName().equals(objectToLookAt)).
                forEach(moveableObjects -> callBackFunctions.toOutput(moveableObjects.getLook()));
        callBackFunctions.
                getInventory().stream().
                filter(moveableObjects -> moveableObjects.getName().equals(objectToLookAt)).
                forEach(moveableObjects -> callBackFunctions.toOutput(moveableObjects.getLook()));
    }
}
