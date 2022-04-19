package mqadventure.data;

import java.util.ArrayList;
import java.util.List;

public class Persons extends DefaultObject{

    private String talk="Doesn't say much";
    private String talk_when_done="No signs of interrest";
    private boolean job_done=false;
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

    public String getTalk_when_done() {
        return talk_when_done;
    }

    public void setTalk_when_done(String talk_when_done) {
        this.talk_when_done = talk_when_done;
    }

    public boolean isJob_done() {
        return job_done;
    }
    public void setJob_done(boolean job_done) {
        this.job_done = job_done;
    }

    public List<MoveableObjects> getReceive() {
        return receive;
    }

    public void setReceive(List<MoveableObjects> receive) {
        this.receive = receive;
    }
}
