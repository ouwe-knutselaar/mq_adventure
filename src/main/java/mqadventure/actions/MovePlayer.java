package mqadventure.actions;

import mqadventure.CallBackFunctions;

import java.util.List;

public class MovePlayer extends Callback implements Action{


    public MovePlayer(CallBackFunctions callBackFunctions ) {
        super(callBackFunctions);
    }

    public void execute(List<String> parameters) {
        callBackFunctions.changeRoom(parameters.get(0));
    }


}
