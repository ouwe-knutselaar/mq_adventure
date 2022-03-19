package mqadventure.actions;

import mqadventure.CallBackFunctions;

import java.util.List;

public class Inventory extends Callback implements Action{

    public Inventory(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> parameters) {
        callBackFunctions.toOutput("You have:");
        callBackFunctions.
                getInventory().
                forEach(inventoryObject -> callBackFunctions.toOutput(inventoryObject.getName()));
        callBackFunctions.toOutput("");
    }
}
