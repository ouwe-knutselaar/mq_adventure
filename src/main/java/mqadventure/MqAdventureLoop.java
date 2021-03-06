package mqadventure;

import mqadventure.actions.*;
import mqadventure.data.DataControl;
import mqadventure.data.MoveableObjects;
import mqadventure.data.Room;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class MqAdventureLoop {

    private DataControl dataControl;
    private Map<String, Action> actionList = new HashMap<String, Action>();
    private StringBuilder outputCollector;
    private enum GAMESTATES {BEGIN,GIVE_PASSWORD,PLAYING,END}
    private GAMESTATES currentGameState = GAMESTATES.BEGIN;
    private StringBuilder response = new StringBuilder();
    private boolean hasWon = false;
    private boolean hasStopped = false;
    private String userName;
    private MessageDigest digest;

    public CallBackFunctions callBackFunctions = new CallBackFunctions(){
        public void changeRoom(String room) {
            changeToNewRoom(room);
        }

        @Override
        public List<MoveableObjects> getInventory() {
            return dataControl.Inventory();
        }

        @Override
        public Room getCurrentRoom() {
            return dataControl.getCurrentRoom();
        }

        @Override
        public void toOutput(String output) {
            outputCollector.append(output).append(System.lineSeparator());
        }

        @Override
        public void finish() {
            currentGameState=GAMESTATES.END;
        }

        @Override
        public void showRoom() {
            showCurrentRoom();
        }

        @Override
        public void showHelp() {
            outputCollector.append(dataControl.getHelpMessage()).append(System.lineSeparator());
        }

        @Override
        public boolean isTheOutputEmpty() {
            return outputCollector.toString().length() >0;
        }
    };


    public String getResponse(){
        return response.toString();
    }

    public boolean hasPlayerWon(){
        return hasWon;
    }

    public boolean hasPlayerStopped(){
        return hasStopped;
    }

    public void sendInput(String input){
        switch (currentGameState){

            case BEGIN: begin(input);
                        break;

            case GIVE_PASSWORD: givePassword(input);
                                break;

            case PLAYING: play(input);
                          break;

            case END: end(input);
                      break;
        }
    }


    private void begin(String username){
        this.userName = username;
        if(dataControl.isExistingUser(username)) {
            dataControl.loadYamlFile(username);
            dataControl.setCurrentRoomByName(dataControl.getCurrentRoomName());
            response.setLength(0);
            response.append("Welcome ").append(username).append(System.lineSeparator()).append("Give me thy password to save your legacy.....").append(System.lineSeparator());
            currentGameState = GAMESTATES.GIVE_PASSWORD;
            return;
        }
        registerNewUser(username);
        response.setLength(0);
        response.append("Welcome ").append(username).append(System.lineSeparator()).append("We do not know you, but give me thy password to secure your legacy.....").append(System.lineSeparator());
        currentGameState = GAMESTATES.GIVE_PASSWORD;
    }

    private void registerNewUser(String userName) {
        this.userName = userName;
        dataControl.setName(userName);
        dataControl.writeYamlFile(userName);
    }


    private void givePassword(String password){

        if(dataControl.getPassword().isEmpty()){
            dataControl.setPassword(new String(digest.digest(password.getBytes(StandardCharsets.UTF_8))));
            dataControl.writeYamlFile(userName);
            response.setLength(0);
            response.append("We will keep you magic word. Now enter the adventure").append(System.lineSeparator())
                    .append("Enter 'help' to if you don't know it anymore.").append(System.lineSeparator());
            currentGameState =GAMESTATES.PLAYING;
            return;
        }

        if(dataControl.getPassword().equals(new String(digest.digest(password.getBytes(StandardCharsets.UTF_8))))){
            response.setLength(0);
            response.append("You are allowed to go further with your quest.").append(System.lineSeparator())
                    .append("Enter 'help' to if you don't know it anymore.").append(System.lineSeparator());
            currentGameState =GAMESTATES.PLAYING;
            return;
        }

        response.setLength(0);
        response.append("Thou have not given the right magic word.").append(System.lineSeparator());
    }


    private void end(String input){
        response.setLength(0);
        response.append("Hail ").append(dataControl.getName()).append(", thou have defeated the messaging monster");
        hasWon = true;
    }


    public void init()  {
        try {
            digest = MessageDigest.getInstance("SHA-256");
            dataControl=new DataControl();
            dataControl.setCurrentRoomByName(dataControl.getCurrentRoomName());
            response = response.append(dataControl.getStartMessage());

            actionList.put("north",new MovePlayer(callBackFunctions));
            actionList.put("east",new MovePlayer(callBackFunctions));
            actionList.put("south",new MovePlayer(callBackFunctions));
            actionList.put("west",new MovePlayer(callBackFunctions));
            actionList.put("look",new LookAtObject(callBackFunctions));
            actionList.put("pick",new PickUpObject(callBackFunctions));
            actionList.put("help",new Help(callBackFunctions));
            actionList.put("inventory",new Inventory(callBackFunctions));
            actionList.put("drop",new DropObject(callBackFunctions));
            actionList.put("use",new UseObjectOn(callBackFunctions));
            actionList.put("read",new ReadObject(callBackFunctions));
            actionList.put("open",new OpenObject(callBackFunctions));
            actionList.put("talk",new TalkToObject(callBackFunctions));
            actionList.put("give",new GiveObject(callBackFunctions));
            actionList.put("cast",new CastSpell(callBackFunctions));
            actionList.put("say",new CastSpell(callBackFunctions));
            actionList.put("exit",parameters -> saveAndExitGame());
            actionList.put("save",parameters -> saveGame());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private Action saveGame() {
        dataControl.writeYamlFile(userName);
        response.append(userName+", Thou lecagy is saved for eternity");
        return null;
    }

    private Action saveAndExitGame() {
        dataControl.writeYamlFile(userName);
        response.append("Thou lecagy is saved for eternity and thou can leave this world behind");
        hasStopped = true;
        return null;
    }

    private void play(String input) {
        input = input.toLowerCase();

        response.setLength(0);
        List<String> inputArray = new LinkedList<>(Arrays.asList(input.split("\\s")));
        if(inputArray.isEmpty())return;
        outputCollector=new StringBuilder();
        actionList.entrySet().
                stream().
                filter(entry -> entry.getKey().equals(inputArray.get(0))).
                forEach(entry -> entry.getValue().execute(inputArray));
        response.append(outputCollector.toString());
        if(dataControl.getCurrentRoomName().equals("endpoint")){
            currentGameState=GAMESTATES.END;
            hasWon = true;
        }
    }

    private void showCurrentRoom(){
        response.append(dataControl.getCurrentRoom().getDescription()).append(System.lineSeparator());
        dataControl.getCurrentRoom().getMoveable_objects().forEach(object -> response.append(object.getDescription()).append(System.lineSeparator()));
        dataControl.getCurrentRoom().getPersons().forEach(person -> response.append(person.getDescription()).append(System.lineSeparator()));
        dataControl.getCurrentRoom().getJunctions().forEach(junction -> response.append(junction.getDescription()).append(System.lineSeparator()));
        dataControl.getCurrentRoom().getMonster().stream().filter(monster -> monster.getLife()>0).forEach(monster -> response.append(monster.getDescription()).append(System.lineSeparator()));
    }

    public void changeToNewRoom(String direction){
        Room tempRoom = dataControl.getCurrentRoom();
        if(!tempRoom.getJunctions().stream().filter(junction -> junction.getName().equals(direction)).findFirst().isPresent()){
            response.append("You cannot go "+direction);
            return;
        }

        dataControl.getCurrentRoom().getJunctions().
                stream().
                filter(junction -> junction.getName().equals(direction)).
                filter(junction -> !junction.getBlocked().equals("yes")).
                forEach(junction -> dataControl.setCurrentRoomByName(dataControl.getRoomByName(junction.getGoes_to()).getName()));
        tempRoom.getJunctions().
                stream().
                filter(junction -> junction.getName().equals(direction)).
                filter(junction -> junction.getBlocked().equals("yes")).
                forEach(junction -> response.append("You cannot go "+direction+", it is blocked."));
    }


}
