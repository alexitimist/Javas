package pl.pjatk.zoo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Health health;
    private String specimen;
    private boolean hunger;
    private Diet diet;
    private Type type;
    public Animal() {
        this.id = 0;
        this.health = Health.HEALTHY;
        this.specimen = "Pingwin";
        this.hunger = false;
        this.diet=Diet.MEAT;
        this.type=Type.LAND;
    }

    public Animal(Integer id, Health health, String specimen, boolean hunger, Diet diet, Type type) {
        this.id = id;
        this.health = health;
        this.specimen = specimen;
        this.hunger = hunger;
        this.diet = diet;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public String getSpecimen() {
        return specimen;
    }

    public void setSpecimen(String specimen) {
        this.specimen = specimen;
    }

    public boolean isHunger() {
        return hunger;
    }

    public void setHunger(boolean hunger) {
        this.hunger = hunger;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", health=" + health +
                ", specimen='" + specimen + '\'' +
                ", hunger=" + hunger +
                ", diet=" + diet +
                ", type=" + type +
                '}';
    }
}
