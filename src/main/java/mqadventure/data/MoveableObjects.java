package mqadventure.data;

public class MoveableObjects extends DefaultObject {

    private String look;
    private MoveableObjects open;

    public String getLook() {
        return look;
    }
    public void setLook(String look) {
        this.look = look;
    }

    public MoveableObjects getOpen() {
        return open;
    }
    public void setOpen(MoveableObjects open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "MoveableObjects{" +
                "look='" + look + '\'' +
                ", open=" + open +
                ", name=" + this.getName() +
                ", description=" + this.getDescription() +
                '}';
    }
}
