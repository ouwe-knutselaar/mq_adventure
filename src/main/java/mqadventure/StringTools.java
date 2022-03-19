package mqadventure;

import mqadventure.data.Junction;
import mqadventure.data.MoveableObjects;

import java.util.ArrayList;
import java.util.List;

public class StringTools {

    public static String paddString(String input,int padToSize){
        return paddStringWithChar(input,padToSize,' ');
    }

    public static String paddStringWithChar(String input,int padToSize,char padChar){
        StringBuilder output =new StringBuilder(input);
        for(int tel=input.length();tel<padToSize;tel++)output.append(padChar);
        return output.toString();
    }

    public static String makeJunctionStringList(List<Junction> junctionList){
        StringBuilder junctionStringList = new StringBuilder();
        junctionList.forEach(junction -> junctionStringList.append(junction.getName().charAt(0)));
        return junctionStringList.toString();
    }

    public static String makeObjectStringList(List<MoveableObjects> objectList) {
        StringBuilder objectStringList = new StringBuilder();
        objectList.forEach(object -> objectStringList.append(object.getName().charAt(0)));
        return objectStringList.toString();
    }

    public static String stringArrayToString(List<String> inputListOfStrings,int offset){
        for (int i=0;i<offset;i++)inputListOfStrings.remove(i);
        return String.join(" ", new ArrayList<>(inputListOfStrings));
    }

}
