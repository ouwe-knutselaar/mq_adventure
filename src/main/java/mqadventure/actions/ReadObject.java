package mqadventure.actions;

import mqadventure.CallBackFunctions;
import mqadventure.StringTools;

import java.util.List;

public class ReadObject extends Callback implements Action{

    public ReadObject(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> objectToReadFromList) {
        if(objectToReadFromList.size() == 1){
            callBackFunctions.toOutput("read what ??");
            return;
        }

        String objectToReadFrom = StringTools.stringArrayToString(objectToReadFromList,1);

        if(callBackFunctions.getCurrentRoom().getRead().missingObjectNamed(objectToReadFrom)){
            callBackFunctions.toOutput("You are not able to read a "+objectToReadFrom);
            return;
        }

        callBackFunctions.toOutput(callBackFunctions.getCurrentRoom().getRead().getElementWithName(objectToReadFrom).getDescription());
    }
}
