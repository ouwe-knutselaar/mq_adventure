package mqadventure.actions;

import mqadventure.CallBackFunctions;
import mqadventure.data.MoveableObjects;
import java.util.List;
import java.util.stream.Collectors;

public class UseObjectOn extends Callback implements Action {
    public UseObjectOn(CallBackFunctions callBackFunctions) {
        super(callBackFunctions);
    }

    @Override
    public void execute(List<String> parameters) {
        if(parameters.size()<4){
            callBackFunctions.toOutput("What?");
            return;
        }
        String objectToUse = extractbjectToUse(parameters);
        String subjectToUseOn = extractSubjectToUseOn(parameters);

        if(!callBackFunctions.getInventory().stream().filter(object -> object.getName().equals(objectToUse)).findFirst().isPresent()){
            callBackFunctions.toOutput("You don't have a "+objectToUse);
            return;
        }

        if(!callBackFunctions.getCurrentRoom().getJunctions().stream().filter(junction -> junction.getObject_name().equals(subjectToUseOn)).findFirst().isPresent()){
            callBackFunctions.toOutput("There is no "+subjectToUseOn);
            return;
        }

        if(!callBackFunctions.getCurrentRoom().getJunctions().stream().filter(junction -> junction.getUnblock().equals(objectToUse)).findFirst().isPresent()){
            callBackFunctions.toOutput("That doesn't work");
            return;
        }

        useObjectOn(objectToUse, subjectToUseOn);

    }

    public void useObjectOn(String objectToUSe, String subjectToUseOn) {
        callBackFunctions.
                getCurrentRoom().
                getJunctions().
                stream().
                filter(junction -> junction.getUnblock().equals(objectToUSe)).
                filter(junction -> junction.getObject_name().equals(subjectToUseOn)).
                        forEach(junction -> {
                            junction.setBlocked("no");
                            callBackFunctions.toOutput("You unlocked the " + subjectToUseOn + "\n");
                        });
    }

    private String extractbjectToUse(List<String> parameters) {
        String ObjectToUse = "";
        int OnLocation = parameters.indexOf("on");
        for (int tel = 1; tel < OnLocation; tel++) {
            ObjectToUse = ObjectToUse + parameters.get(tel) + " ";
        }
        ObjectToUse = ObjectToUse.trim();
        return ObjectToUse;
    }

    private String extractSubjectToUseOn(List<String> parameters) {
        String SubjectToUseOn = "";
        int OnLocation = parameters.indexOf("on")+1;
        for(int tel = OnLocation;tel<parameters.size();tel++){
            SubjectToUseOn=SubjectToUseOn+parameters.get(tel)+" ";
        }
        SubjectToUseOn = SubjectToUseOn.trim();
        return SubjectToUseOn;
    }
}