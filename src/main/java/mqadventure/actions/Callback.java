package mqadventure.actions;

import mqadventure.CallBackFunctions;

public abstract class Callback {

    CallBackFunctions callBackFunctions;

    protected Callback(CallBackFunctions callBackFunctions){
        this.callBackFunctions = callBackFunctions;
    }

}
