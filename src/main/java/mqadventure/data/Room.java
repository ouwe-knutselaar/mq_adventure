package mqadventure.data;

import mqadventure.addon.AdvList;
import mqadventure.data.map.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Room extends DefaultObject {
    private AdvList<Junction> junctions = new AdvList<>();
    private AdvList<Look> look = new AdvList<>();
    private AdvList<MoveableObjects> moveable_objects = new AdvList<>();
    private AdvList<Persons> persons = new AdvList<>();
    private AdvList<Spell> spell = new AdvList<>();
    private AdvList<Read> read = new AdvList<>();
    private List<Monster> monster = new ArrayList<>();

    private Coordinates coordinates;


    public AdvList<Junction> getJunctions() {
        return junctions;
    }
    public void setJunctions(AdvList<Junction> junctions) {
        this.junctions = junctions;
    }

    public AdvList<Look> getLook() {
        return look;
    }
    public void setLook(AdvList<Look> look) {
        this.look = look;
    }

    public AdvList<MoveableObjects> getMoveable_objects() {
        return moveable_objects;
    }
    public void setMoveable_objects(AdvList<MoveableObjects> moveable_objects) {
        this.moveable_objects = moveable_objects;
    }

    public AdvList<Persons> getPersons() {
        return persons;
    }
    public void setPersons(AdvList<Persons> persons) {
        this.persons = persons;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(int x, int y) {
        this.coordinates = new Coordinates(x,y);
    }

    public void resetCoordinates(){
        this.coordinates = null;
    }

    public AdvList<Read> getRead() {
        return read;
    }
    public void setRead(AdvList<Read> read) {
        this.read = read;
    }

    public AdvList<Spell> getSpell() {
        return spell;
    }
    public void setSpell(AdvList<Spell> spell) {
        this.spell = spell;
    }

    public List<Monster> getMonster() {
        return monster;
    }
    public void setMonster(List<Monster> monster) {
        this.monster = monster;
    }

    Optional<Junction> getRoomNameOfJuntionTo(String direction){
        return junctions.stream().
                    filter(junction -> junction.
                            getGoes_to().
                            equals(direction)).
                    findFirst();
    }

    public boolean hasJunctionTo(String direction){
        for(Junction junction : junctions){
            if(junction.getName().equals(direction))return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name=" + this.getName() +
                ", junctions=" + junctions +
                ", look=" + look +
                ", moveable_objects=" + moveable_objects +
                ", persons=" + persons +
                ", coordinates=" + coordinates +
                '}';
    }

    public String view(){
        StringBuilder output = new StringBuilder();
        output.append(this.getName()).append(System.lineSeparator()).
                append(this.getDescription()).append(System.lineSeparator()).
                append(junctions).append(System.lineSeparator()).
                append(persons).append(System.lineSeparator()).
                append(moveable_objects).append(System.lineSeparator());
        return output.toString();
    }
}
