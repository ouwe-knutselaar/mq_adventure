package mqadventure;

import mqadventure.data.map.AdventureMap;
import java.util.Scanner;

public class Design {

    private AdventureMap adventureMap;

    public static void main(String [] argv){
        Design design = new Design();
        design.init();
        design.loop();
    }

    private void loop() {
        Scanner scan = new Scanner(System.in);
        boolean loop =true;
        while(loop){
            System.out.print(">");
            String readLine = scan.nextLine();
            String command=getCommand(readLine);
            switch(command){
                case "map": adventureMap.printMap();
                            break;
                case "add": addRoom(getParameter(readLine,1),getParameter(readLine,2),getParameter(readLine,3));
                            break;
                case "view": viewRoom(getParameter(readLine,1))  ;
                             break;
                case "save": adventureMap.writeYaml();
                             break;
                case "quit": loop=false;
                            break;
            }
        }
    }

    private void viewRoom(String roomName) {
        if(adventureMap.doesRoomExists(roomName)) {
            System.out.println(adventureMap.getRoomByName(roomName).view());
        }
        else System.out.println("room does not exists");
    }

    private void addRoom(String newRoomName,String direction, String connectingRoom) {
        System.out.println("Add room "+newRoomName+", "+direction+ " of "+connectingRoom );
        System.out.println(
                adventureMap.addNewRoom(newRoomName,direction,connectingRoom)
        );
    }

    private void init() {
        adventureMap = new AdventureMap();
    }

    private String getCommand(String readLine){
        return readLine.toLowerCase().split("\\s")[0];
    }

    private String getParameter(String readLine,int pos){
        String [] list = readLine.toLowerCase().split("\\s");
        if(list.length<pos+1)return "";
        return list[pos];
    }

}
