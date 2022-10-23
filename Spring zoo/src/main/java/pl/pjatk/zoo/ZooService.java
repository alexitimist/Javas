package pl.pjatk.zoo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZooService {
    private final ZooRepository zoorepo;
    private final AnimalRepository anrepo;

    public ZooService(ZooRepository zoorepo, AnimalRepository anrepo) {
        this.zoorepo = zoorepo;
        this.anrepo = anrepo;
    }

    public Zoo getEmptyZoo() {
        return new Zoo(1, "FajneZoo", "Gdansk", false, null);
    }
    public Zoo getExampleZoo() {
        List<Animal> animals = new ArrayList<>();
        Animal dog = new Animal(null,Health.HEALTHY,"mammal",false,Diet.MEAT,Type.LAND);
        animals.add(dog);
        Zoo zoo = new Zoo(null, "FajneZoo", "Gdansk", false, animals);
        return zoorepo.save(zoo);

    }

    public List<Zoo> getListZoo(){
        //return (List<Zoo>) zoorepo.findAll();
        return (List<Zoo>)zoorepo.findAllByIdGreaterThan(5);
    }
    public Zoo getEmptyNamedZoo(String name) {
        Zoo zoo = new Zoo(1, name, "Gdansk", false, null);
        return zoo;
    }

    public void resetLocation(Zoo zoo) {
        if (zoo.getLocation() != null) {
            zoo.setLocation(null);
        }
    }
    public void addPrefixToName(Zoo zoo, String prefix)
    {
        if(zoo.getName() != null)
        {
            zoo.setName(prefix+zoo.getName());
        }
    }
    public void changeToClosed(Zoo zoo)
    {
        if(zoo.getIsclosed()!=false)
        {zoo.setIsclosed(!false);}
    }
    public void addAnimal(Zoo zoo, Animal animal){
        if(!zoo.getAnimal().isEmpty())
        {
            List<Animal> list = zoo.getAnimal();
            list.add(animal);
            zoo.setAnimal(list);
        }
    }
    public void closeIfUnlucky(Zoo zoo)
    {
        if(zoo.getId()==13||zoo.getLocation().equals("Poland"))
        {
            zoo.setIsclosed(true);
        }
    }
    public void reviveDeadZoo(Zoo zoo)
    {
        if(zoo.getAnimal().isEmpty())
        {
            List<Animal> list = null;
            list.add(new Animal());
            zoo.setAnimal(list);
        }
    }
}
