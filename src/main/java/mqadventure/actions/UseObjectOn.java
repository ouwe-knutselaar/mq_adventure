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
        try {
            String objectToUse = extractbjectToUse(parameters);
            String subjectToUseOn = extractSubjectToUseOn(parameters);
            useObjectOn(objectToUse, subjectToUseOn);
        } catch (IndexOutOfBoundsException e) {
            callBackFunctions.toOutput("what?");
        }
    }

    public void useObjectOn(String objectToUSe, String subjectToUseOn) {
        List<MoveableObjects> objectToUSeList = callBackFunctions.getInventory().stream().filter(object -> object.getName().equals(objectToUSe)).collect(Collectors.toList());

        objectToUSeList.
                forEach(objectToUse -> callBackFunctions.
                        getCurrentRoom().
                        getJunctions().
                        stream().
                        filter(junction -> junction.getUnblock().equals(objectToUSe)).
                        filter(junction -> junction.getObject_name().equals(subjectToUseOn)).
                        forEach(junction -> {
                            junction.setBlocked("no");
                            callBackFunctions.toOutput("You unlocked the " + subjectToUseOn + "\n");
                        }));
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