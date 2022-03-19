package mqadventure.data;

public class Junction extends DefaultObject {

    private String goes_to;
    private String blocked="no";
    private String unblock="none";
    private String object_name="";
    private String spell="";

    public Junction(){};

    public Junction(String name,String goes_to){
        this.setName(name);
        this.setDescription("new junction");
        this.goes_to = goes_to;
    }

    public String getGoes_to() {
        return goes_to;
    }

    public void setGoes_to(String goes_to) {
        this.goes_to = goes_to.toLowerCase();
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public String getUnblock() {
        return unblock;
    }

    public void setUnblock(String unblock) {
        this.unblock = unblock;
    }

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(String object_name) {
        this.object_name = object_name.toLowerCase();
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell.toLowerCase();
    }

    @Override
    public String toString() {
        return "Junction{" +
                " name='" + this.getName() + '\'' +
                ", goes_to='" + goes_to + '\'' +
                ", blocked='" + blocked + '\'' +
                ", unblock='" + unblock + '\'' +
                ", object_name='" + object_name + '\'' +
                '}';
    }
}
