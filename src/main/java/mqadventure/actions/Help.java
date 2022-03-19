package mqadventure.actions;

import mqadventure.CallBackFunctions;
import java.util.List;

public class Help extends Callback implements Action{

    public Help(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> parameters) {
        callBackFunctions.showHelp();
    }
}
