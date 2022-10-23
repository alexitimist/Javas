package pl.pjatk.zoo;

import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal,Integer> {
    Animal findById(long id);
}
