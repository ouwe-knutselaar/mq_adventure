package mqadventure.actions;

import mqadventure.data.Room;

import java.util.List;

public interface Action {
    void execute(List<String> parameters);
}
