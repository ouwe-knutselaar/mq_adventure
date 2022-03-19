package mqadventure.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Monster extends DefaultObject{

    private int life = 1;
    private List<String> damaging_spells = new ArrayList<>();
    private String when_dead_phrase = "The monster is dead";

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public List<String> getDamaging_spells() {
        return damaging_spells;
    }
    public void setDamaging_spells(List<String> damaging_spels) {
        this.damaging_spells = damaging_spels.stream().map(spell -> spell.toLowerCase()).collect(Collectors.toList());
    }


    public String getWhen_dead_phrase() {
        return when_dead_phrase;
    }
    public void setWhen_dead_phrase(String when_dead_phrase) {
        this.when_dead_phrase = when_dead_phrase;
    }

    public String castSpellToMonster(String casted_spell){
        if(life == 0)return "You cannot hurt a dead monster anymore";
        if(!damaging_spells.contains(casted_spell))return "pffftttt  your spell does nothing";

        damaging_spells.removeIf(spell ->spell.equals(casted_spell));
        life--;

        if(life==0)return when_dead_phrase;
        return "You hurt the monster badly";
    }
}
