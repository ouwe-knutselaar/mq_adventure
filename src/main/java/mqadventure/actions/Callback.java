package mqadventure.actions;

import mqadventure.CallBackFunctions;

public abstract class Callback {

    CallBackFunctions callBackFunctions;

    public Callback(CallBackFunctions callBackFunctions){
        this.callBackFunctions = callBackFunctions;
    }

}
