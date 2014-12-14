package entity;

import java.util.List;

public class Pizza {
    private final String name;
    private final String size;
    private final List<Topping> toppings;

    public Pizza(String name, String size, List<Topping> toppings) {
        this.name = name;
        this.size = size;
        this.toppings = toppings;
    }

    public String getSize() {
        return size;
    }

    public int getNoOfToppings() {
        return toppings.size();
    }

    @Override
    public String toString() {
        return size + " size " + name + " with " + getNoOfToppings() + " toppings";
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Pizza)) return false;
        Pizza currPizza = (Pizza)obj;
        if(currPizza.getName().equals(name) && currPizza.getSize().equals(size)) return true;
        return false;
    }
}
