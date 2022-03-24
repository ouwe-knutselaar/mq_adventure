package mqadventure.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DataControl {

    private Adventure adventure;
    private ObjectMapper mapper;
    private String sourceDir;
    private Room currentRoom;

    public DataControl(){
        sourceDir = Paths.get(".").toAbsolutePath().normalize().toString();
        mapper = new ObjectMapper(new YAMLFactory());
        mapper.configure(JsonParser.Feature.IGNORE_UNDEFINED,true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            adventure = mapper.readValue(this.getClass().getClassLoader().getResourceAsStream("map_start.yml"),Adventure.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void loadYamlFile(String yamlFile) {
        try {
            adventure = mapper.readValue(new File(sourceDir+"\\"+yamlFile+".yaml"),Adventure.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void writeYamlFile(String yamlFile) {
        try {
            File newYamlFile = new File(sourceDir+"\\"+yamlFile+".yaml");
            newYamlFile.delete();
            mapper.writeValue(newYamlFile, adventure);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public boolean isExistingUser(String username) {
        String file=sourceDir+"\\"+username+".yaml";
        boolean exists=  Paths.get(file).toFile().exists();
        return exists;
    }

    public Room getRoomByName(String roomName) {
        for(Room room : adventure.getRooms()){
            if(room.getName().equals(roomName))return room;
        }
        throw new IllegalArgumentException("A room named "+roomName+" does not exist");
    }

    public void addRoom(Room newRoom){
        adventure.addRoom(newRoom);
    }
    public List<Room> listOfRoom(){
        return adventure.getRooms();
    }
    public boolean doesRoomExists(String roomName){
        for(Room room :adventure.getRooms()) {
            if(room.getName().equals(roomName))return true;
        }
        return false;
    }
    public void setCurrentRoomByName(String roomName){
        currentRoom = getRoomByName(roomName);
        adventure.setCurrentRoomName(roomName);
    }
    public Room getCurrentRoom(){
        return currentRoom;
    }

    public String getCurrentRoomName(){
        return adventure.getCurrentRoomName();
    }

    public String getStartMessage(){return adventure.getStartMessage();}
    public String getHelpMessage() {return adventure.getHelpMessage();}

    public String getName() {
        return adventure.getName();
    }
    public void setName(String name) {
        adventure.setName(name);
    }

    public String getPassword() {
        return adventure.getPassword();
    }
    public void setPassword(String password) {
        adventure.setPassword(password);
    }

    public void addToInventoryList(MoveableObjects moveableObjects){
        adventure.getInventory().add(moveableObjects);
    }
    public List<MoveableObjects> Inventory(){
        return adventure.getInventory();
    }
    public List<MoveableObjects> getObjectFromInventory(String objectName){
        return adventure.getInventory().stream().filter(object -> object.getName().equals(objectName)).collect(Collectors.toList());
    }


}
