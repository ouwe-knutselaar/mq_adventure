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
        String ObjectToGive = "";
        int OnLocation = parameters.indexOf("to");
        for(int tel = 1;tel<OnLocation;tel++){
            ObjectToGive=ObjectToGive+parameters.get(tel)+" ";
        }
        ObjectToGive = ObjectToGive.trim();
        return ObjectToGive;
    }

    private String personToGiveTo(List<String> parameters){
        String PersonToGiveTo = "";
        int OnLocation = parameters.indexOf("to")+1;
        for(int tel = OnLocation;tel<parameters.size();tel++){
            PersonToGiveTo=PersonToGiveTo+parameters.get(tel)+" ";
        }
        PersonToGiveTo = PersonToGiveTo.trim();
        return PersonToGiveTo;
    }

}
