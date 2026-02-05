package battleship.ship; //ahmed

import java.util.*;

public abstract class Ship {
    protected String name;
    protected int size;
    protected Set<String> coordinates = new HashSet<>();

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void setCoordinates(List<String> coords) {
        coordinates.addAll(coords);
    }

    public boolean isSunk() {
        return coordinates.isEmpty();
    }

    public String getName() { return name; }
    public int getSize() { return size; }

    public Set<String> getCoordinates() {
        return coordinates;
    }

    public void hitAt(String coord) {
        coordinates.remove(coord);
    }
}
