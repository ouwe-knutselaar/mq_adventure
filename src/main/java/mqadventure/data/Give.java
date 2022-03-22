package mqadventure.data;

import java.util.ArrayList;
import java.util.List;

public class Give {

    private String object="";
    private String response="";
    private List<MoveableObjects> receive = new ArrayList<>();

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object.toLowerCase();
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<MoveableObjects> getReceive() {
        return receive;
    }

    public void setReceive(List<MoveableObjects> receive) {
        this.receive = receive;
    }
}
