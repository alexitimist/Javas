package pl.pjatk.zoo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ZooRepository extends CrudRepository<Zoo,Integer> {

List<Zoo> findAllByIdGreaterThan(int id);
}