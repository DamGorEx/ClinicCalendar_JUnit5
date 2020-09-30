package def;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Patien {
    private String name, surname;
    private int height, weight;
    private static Map<String, Patien> patiens = new HashMap<>();
    private Patien(String name, String surname, int height, int weight) {

            setName(name);
            setSurname(surname);
            setHeight(height);
            setWeight(weight);
    }
    public static Patien of(String name, String surname, int height, int weigth) {
        Patien p = patiens.get(name + " " + surname);
        if (p != null) {
            int patienHeigth = p.getHeight();
            int patienWeight = p.getWeight();
            if (patienHeigth != height) {
                p.setHeight(height);
            }
            if (patienWeight != weigth) {
                p.setWeight(weigth);
            }
            return p;
        } else {
            p = new Patien(name, surname, height, weigth);
            patiens.put(name + " " + surname, p);
            return p;
        }
    }

    public void setWeight(int weight) {
        Objects.requireNonNull(weight);
        this.weight = weight;
    }

    public void setHeight(int height) {
        Objects.requireNonNull(height);
        this.height = height;
    }

    public void setSurname(String surname) {
        Objects.requireNonNull(surname);
        this.surname = surname;
    }

    public void setName(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }
}
