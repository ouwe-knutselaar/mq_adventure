package mqadventure.data;

import java.util.ArrayList;
import java.util.List;

public class Adventure {

    private List<Room> rooms;
    private List<MoveableObjects> inventory = new ArrayList<>();
    private String startMessage="Start message";
    private String helpMessage="help message";
    private String name="noname";
    private String password = "";
    private String currentRoomName ="startingpoint";

    public List<Room> getRooms() {
        return rooms;
    }
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getStartMessage() {
        return startMessage;
    }
    public void setStartMessage(String startMessage) {
        this.startMessage = startMessage;
    }

    public String getHelpMessage() {
        return helpMessage;
    }
    public void setHelpMessage(String helpMessage) {
        this.helpMessage = helpMessage;
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<MoveableObjects> getInventory() {
        return inventory;
    }
    public void setInventory(List<MoveableObjects> inventory) {
        this.inventory = inventory;
    }

    public String getCurrentRoomName() {
        return currentRoomName;
    }

    public void setCurrentRoomName(String currentRoomName) {
        this.currentRoomName = currentRoomName;
    }
}
