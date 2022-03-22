package mqadventure.actions;

import mqadventure.CallBackFunctions;
import mqadventure.StringTools;

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
        AtomicBoolean hasNoMatch= new AtomicBoolean(true);
        callBackFunctions.
                getCurrentRoom().getLook().stream().
                filter(look -> look.getName().equals(objectToLookAt)).
                forEach(look -> {
                    callBackFunctions.toOutput(look.getDescription());
                    hasNoMatch.set(false);
                });
        callBackFunctions.
                getCurrentRoom().getMoveable_objects().stream().
                filter(moveableObjects -> moveableObjects.getName().equals(objectToLookAt)).
                forEach(moveableObjects -> {
                    callBackFunctions.toOutput(moveableObjects.getLook());
                    hasNoMatch.set(false);
                });
        callBackFunctions.
                getInventory().stream().
                filter(moveableObjects -> moveableObjects.getName().equals(objectToLookAt)).
                forEach(moveableObjects -> {
                    callBackFunctions.toOutput(moveableObjects.getLook());
                    hasNoMatch.set(false);
                });

        if(hasNoMatch.get()){
            callBackFunctions.toOutput("There is no "+objectToLookAt);
            return;
        }
    }
}
