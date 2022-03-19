package mqadventure.data.map;

import mqadventure.StringTools;
import mqadventure.data.DataControl;
import mqadventure.data.Junction;
import mqadventure.data.Room;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdventureMap {

    //private Map<String, Room> adventureMap = new HashMap<>();
    private int maxx,maxy;
    private int maxNameSize;
    DataControl dataControl;
    Map<String,String> oppositeDirection = Stream.
            of(new String[][] {{ "north", "south" }, { "east", "west" },{"south","north"},{"west","east"}}).
            collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public AdventureMap(){
        dataControl = new DataControl();
        buildTheMap();

    }

    private void buildTheMap() {
        Room currentRoom = dataControl.getRoomByName("startingpoint");
        addAllRoomToAdventureMap(dataControl,0,0,currentRoom.getName(),currentRoom);
        offSetMap();
    }

    private void addAllRoomToAdventureMap(DataControl dataControl, int x, int y, String newRoomName, Room room) {
        if (dataControl.getRoomByName(newRoomName).getCoordinates() !=null)return;
        room.setCoordinates(x, y);
        System.out.println(room.getName() + " X:" + x + " Y:" + y);
        for (Junction junction : room.getJunctions()) {
            String direction = junction.getName();
            if (junction.getGoes_to().equals(room.getName())) continue;
            switch (direction) {
                case "north":
                    addAllRoomToAdventureMap(dataControl, x, y - 1, junction.getGoes_to(), dataControl.getRoomByName(junction.getGoes_to()));
                case "south":
                    addAllRoomToAdventureMap(dataControl, x, y + 1, junction.getGoes_to(), dataControl.getRoomByName(junction.getGoes_to()));
                case "east":
                    addAllRoomToAdventureMap(dataControl, x + 1, y, junction.getGoes_to(), dataControl.getRoomByName(junction.getGoes_to()));
                case "west":
                    addAllRoomToAdventureMap(dataControl, x - 1, y, junction.getGoes_to(), dataControl.getRoomByName(junction.getGoes_to()));
            }
        }
    }

    public String addNewRoom(String newRoomName,String direction, String ofRoomName){
        Room ofroom= dataControl.getRoomByName(ofRoomName);
        if(ofroom.hasJunctionTo(direction))return "Connecting room has already a junction to "+direction;

        Room newRoom=new Room();
        newRoom.setName(newRoomName);
        newRoom.setDescription("new description");
        newRoom.getJunctions().add(new Junction(oppositeDirection.get(direction),ofRoomName));
        dataControl.addRoom(newRoom);

        ofroom.getJunctions().add(new Junction(direction,newRoomName));
        dataControl.listOfRoom().forEach(room -> room.resetCoordinates());
        buildTheMap();
        return "done";
    }

    private void offSetMap() {
        int xOffset=0;
        int yOffset=0;

        for(Room room : dataControl.listOfRoom()){
            if(room.getCoordinates().getX()<xOffset)xOffset=room.getCoordinates().getX();
            if(room.getCoordinates().getY()<yOffset)yOffset=room.getCoordinates().getY();
            if(room.getCoordinates().getX()>maxx)maxx=room.getCoordinates().getX();
            if(room.getCoordinates().getY()>maxy)maxy=room.getCoordinates().getY();
            if(room.getName().length()>maxNameSize)maxNameSize=room.getName().length();
        };
        xOffset=Math.abs(xOffset);
        yOffset=Math.abs(yOffset);
        int finalXOffset = xOffset;
        int finalYOffset = yOffset;

        dataControl.listOfRoom().forEach(room -> room.setCoordinates(room.getCoordinates().getX()+ finalXOffset,room.getCoordinates().getY()+ finalYOffset));
        maxx=maxx+finalXOffset+1;
        maxy=maxy+finalYOffset+1;

        //adventureMap.forEach((name,room)->System.out.println(name+" X:"+room.getCoordinates().getX()+" Y:"+room.getCoordinates().getY()));
        //System.out.println("maxx="+maxx+" maxy="+maxy);
        //System.out.println("offsetx="+xOffset+" offsety="+yOffset);
    }

    public void printMap(){
        for(int y=0;y<maxy;y++){
            for(int x=0;x<maxx;x++){
                System.out.print("+");
                System.out.print(StringTools.paddStringWithChar("",maxNameSize,'-'));

            }
            System.out.println("+");

            for(int x=0;x<maxx;x++) {
                System.out.print("|");
                System.out.print( StringTools.paddString(getRoomNameByCoordinates(x, y), maxNameSize ) );
            }
            System.out.println("|");

            for(int x=0;x<maxx;x++) {
                System.out.print("|");
                System.out.print(
                        StringTools.paddString(
                                StringTools.makeJunctionStringList(getRoomByCoordinates(x, y).getJunctions()),
                                maxNameSize
                        )
                );
            }
            System.out.println("|");

            for(int x=0;x<maxx;x++) {
                System.out.print("|");
                System.out.print(
                        StringTools.paddString(
                                StringTools.makeObjectStringList(getRoomByCoordinates(x, y).getMoveable_objects()),
                                maxNameSize
                        )
                );
            }

            System.out.println("|");


        }
        for(int x=0;x<maxx;x++){
            System.out.print("+");
            for(int tel=0;tel<maxNameSize;tel++)System.out.print("-");

        }
        System.out.println("+");
    }

    public void writeYaml(){
        dataControl.writeYamlFile("map_test_new.yaml");
    };

    public String getRoomNameByCoordinates(int x, int y){
        Room room = getRoomByCoordinates(x,y);
        if(room == null)return "";
        return room.getName();
    }

    public Room getRoomByCoordinates(int x,int y){
        for(Room room : dataControl.listOfRoom()) {
            if(room.getCoordinates().getX()==x && room.getCoordinates().getY()==y)return room;
        }
        return new Room();
    }

    public Room getRoomByName(String name) {
        return dataControl.getRoomByName(name);
    }

    public boolean doesRoomExists(String name){
        return dataControl.doesRoomExists(name);
    }

}
