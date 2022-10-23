package pl.pjatk.zoo;

import javax.persistence.*;
import java.util.List;
@Entity
public class Zoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;
    private boolean isclosed;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Animal> animal;

    public Zoo() {
        this.id = 0;
        this.name = "PingwinZoo";
        this.location = "Centrum";
        this.isclosed = true;
    }

    public Zoo(Integer id, String name, String location, boolean isClosed, List<pl.pjatk.zoo.Animal> animal) {
        this.id = id;
        this.name = name;
        this.location = location;
        isclosed = isClosed;
        this.animal = animal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean getIsclosed() {
        return isclosed;
    }

    public void setIsclosed(boolean isclosed) {
        this.isclosed = isclosed;
    }

    public List<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(List<Animal> animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", isclosed=" + isclosed +
                ", animal=" + animal +
                '}';
    }
}
