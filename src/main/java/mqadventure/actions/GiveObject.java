package mqadventure.actions;

import mqadventure.CallBackFunctions;
import mqadventure.data.Persons;

import java.util.List;

public class GiveObject extends Callback implements Action {

    public GiveObject(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> parameters) {
        try {
            String objectToGive = extractObjectToGive(parameters);
            String personToGiveObjectTo = personToGiveTo(parameters);
            callBackFunctions.
                    getCurrentRoom().getPersons().stream().
                    filter(persons -> persons.getName().equals(personToGiveObjectTo)).
                    filter(persons -> persons.getGive().getObject().equals(objectToGive)).
                    forEach(person -> giveObectToPerson(person,objectToGive));
        }catch (IndexOutOfBoundsException e){
            callBackFunctions.toOutput("what?");
        }
    }

    private void giveObectToPerson(Persons person, String objectNameToGive){
        callBackFunctions.toOutput(person.getGive().getResponse());
        person.getGive().
                getReceive().
                forEach(objectToReceive -> callBackFunctions.
                        getInventory().
                        add(objectToReceive));
        callBackFunctions.getInventory().removeIf(inventoryObject -> inventoryObject.getName().equals(objectNameToGive));
    }

    private String extractObjectToGive(List<String> parameters){
        StringBuilder objectToGive = new StringBuilder();
        int onLocation = parameters.indexOf("to");
        for(int tel = 1;tel<onLocation;tel++){
            objectToGive.append(parameters.get(tel)).append(" ");
        }
        return objectToGive.toString().trim();
    }

    private String personToGiveTo(List<String> parameters){
        StringBuilder personToGiveTo = new StringBuilder();
        int onLocation = parameters.indexOf("to")+1;
        for(int tel = onLocation;tel<parameters.size();tel++){
            personToGiveTo.append(parameters.get(tel)).append(" ");
        }
        return personToGiveTo.toString().trim();
    }

}
