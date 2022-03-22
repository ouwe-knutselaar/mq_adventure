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
        if(parameters.size() <4){
            callBackFunctions.toOutput("What do you want to give?");
            return;
        }

        String objectToGive = extractObjectToGive(parameters);
        String personToGiveObjectTo = personToGiveTo(parameters);

        List<Persons> personslist = callBackFunctions.getCurrentRoom().getPersons();

        if(!personslist.stream().filter(persons -> persons.getName().equals(personToGiveObjectTo)).findFirst().isPresent()){
            callBackFunctions.toOutput("I don't know "+personToGiveObjectTo);
            return;
        }

        if(!callBackFunctions.getInventory().stream().filter(object -> object.getName().equals(objectToGive)).findFirst().isPresent()){
            callBackFunctions.toOutput("You don't have a "+objectToGive);
            return;
        }

        if(!personslist.stream().filter(person -> person.getGive().getObject().equals(objectToGive)).findFirst().isPresent()){
            callBackFunctions.toOutput(personToGiveObjectTo+" doesn't want to have a "+objectToGive);
            return;
        }

        personslist.stream().
                    filter(persons -> persons.getName().equals(personToGiveObjectTo)).
                    filter(persons -> persons.getGive().getObject().equals(objectToGive)).
                    forEach(person -> giveObectToPerson(person,objectToGive));

    }

    private void giveObectToPerson(Persons person, String objectNameToGive){
        callBackFunctions.toOutput(person.getGive().getResponse());
        person.getGive().getReceive().
                forEach(objectToReceive -> {
                    callBackFunctions.getInventory().add(objectToReceive);
                    callBackFunctions.toOutput(objectToReceive.getDescription());
                });
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
