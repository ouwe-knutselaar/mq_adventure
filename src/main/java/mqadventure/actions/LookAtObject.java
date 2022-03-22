package mqadventure.actions;

import mqadventure.CallBackFunctions;
import mqadventure.StringTools;
import mqadventure.data.Room;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LookAtObject extends Callback implements Action {


    public LookAtObject(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> objectToLookAtList) {
        if(objectToLookAtList.size()==1){
            callBackFunctions.showRoom();
            return;
        }
        lookAt(StringTools.stringArrayToString(objectToLookAtList,1));
    }

    private void lookAt(String objectToLookAt){
        Room currentRoom = callBackFunctions.getCurrentRoom();
        AtomicBoolean hasNoMatch= new AtomicBoolean(true);
        currentRoom.getLook().stream().
                filter(look -> look.getName().equals(objectToLookAt)).
                peek(look -> callBackFunctions.toOutput(look.getDescription())).
                forEach(look ->   hasNoMatch.set(false));

        currentRoom.getMoveable_objects().stream().
                filter(moveableObjects -> moveableObjects.getName().equals(objectToLookAt)).
                peek(moveableObjects -> callBackFunctions.toOutput(moveableObjects.getLook())).
                forEach(moveableObjects -> hasNoMatch.set(false));

        callBackFunctions.
                getInventory().stream().
                filter(moveableObjects -> moveableObjects.getName().equals(objectToLookAt)).
                peek(moveableObjects -> callBackFunctions.toOutput(moveableObjects.getLook())).
                forEach(moveableObjects -> hasNoMatch.set(false));

        if(hasNoMatch.get()){
            callBackFunctions.toOutput("There is no "+objectToLookAt);
            return;
        }
    }
}
