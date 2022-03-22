package mqadventure.data;

import java.util.ArrayList;
import java.util.List;

public class Persons extends DefaultObject{

    private String talk="Doesn't say much";
    private Give give=new Give();
    private List<MoveableObjects> receive = new ArrayList<>();

    public String getTalk() {
        return talk;
    }
    public void setTalk(String talk) {
        //this.talk = talk.toLowerCase();
        this.talk = talk;
    }

    public Give getGive() {
        return give;
    }
    public void setGive(Give give) {
        this.give = give;
    }

    public List<MoveableObjects> getReceive() {
        return receive;
    }

    public void setReceive(List<MoveableObjects> receive) {
        this.receive = receive;
    }
}
