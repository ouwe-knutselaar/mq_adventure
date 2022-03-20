package mqadventure.actions;

import mqadventure.CallBackFunctions;

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
        objectToReadFromList.forEach(objectToReadFrom -> callBackFunctions.
                getCurrentRoom().
                getRead().
                stream().
                filter(readableObject -> readableObject.getName().equals(objectToReadFrom)).
                forEach(read -> callBackFunctions.toOutput(read.getDescription())));

        if(callBackFunctions.isTheOutputEmpty())objectToReadFromList.forEach(objectToReadFrom -> callBackFunctions.toOutput("you cannot read a "+objectToReadFrom));
    }
}
