package mqadventure.addon;

import mqadventure.data.DefaultObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdvList<T extends DefaultObject> extends ArrayList<T> {

    public boolean hasObjectNamed(String name){
        return this.stream().filter(element -> element instanceof DefaultObject).filter(element -> element.getName().equals(name)).findFirst().isPresent();
    }

    public boolean missingObjectNamed(String name){
        return !this.
                stream().
                filter(element -> element instanceof DefaultObject).
                filter(element -> element.getName().equals(name)).
                findFirst().isPresent();
    }

    public T getElementWithName(String name){
        return this.stream().filter(element -> element.getName().equals(name)).findFirst().get();
    }
}
