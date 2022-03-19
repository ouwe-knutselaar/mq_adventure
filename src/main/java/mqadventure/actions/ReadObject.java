package mqadventure.actions;

import mqadventure.CallBackFunctions;

import java.util.List;

public class ReadObject extends Callback implements Action{

    public ReadObject(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> objectToReadFromList) {
        objectToReadFromList.forEach(objectToReadFrom -> callBackFunctions.
                getCurrentRoom().
                getRead().
                stream().
                filter(readableObject -> readableObject.getName().equals(objectToReadFrom)).
                forEach(read -> callBackFunctions.toOutput(read.getDescription())));
    }
}
