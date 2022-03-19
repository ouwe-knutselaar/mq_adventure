package mqadventure;

import mqadventure.data.MoveableObjects;
import mqadventure.data.Room;

import java.util.List;

public interface CallBackFunctions {

    void changeRoom(String room);
    List<MoveableObjects> getInventory();
    Room getCurrentRoom();
    void toOutput(String output);
    void finish();
    void showRoom();
    void showHelp();
}
