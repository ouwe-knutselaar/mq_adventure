package mqadventure.data;

import mqadventure.data.map.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Room extends DefaultObject {
    private List<Junction> junctions = new ArrayList<>();
    private List<Look> look = new ArrayList<>();
    private List<MoveableObjects> moveable_objects = new ArrayList<>();
    private List<Persons> persons = new ArrayList<>();
    private List<Spell> spell = new ArrayList<>();
    private List<Read> read = new ArrayList<>();
    private List<Monster> monster = new ArrayList<>();

    private Coordinates coordinates;


    public List<Junction> getJunctions() {
        return junctions;
    }
    public void setJunctions(List<Junction> junctions) {
        this.junctions = junctions;
    }

    public List<Look> getLook() {
        return look;
    }
    public void setLook(List<Look> look) {
        this.look = look;
    }

    public List<MoveableObjects> getMoveable_objects() {
        return moveable_objects;
    }
    public void setMoveable_objects(List<MoveableObjects> moveable_objects) {
        this.moveable_objects = moveable_objects;
    }

    public List<Persons> getPersons() {
        return persons;
    }
    public void setPersons(List<Persons> persons) {
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

    public List<Read> getRead() {
        return read;
    }
    public void setRead(List<Read> read) {
        this.read = read;
    }

    public List<Spell> getSpell() {
        return spell;
    }
    public void setSpell(List<Spell> spell) {
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
