package pl.pjatk.zoo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ZooServiceTest {
    ZooService zooservice = new ZooService(null,null);
    @Test
    void addPrefixToName()
    {
        Zoo zoo = new Zoo(1,"Gdanskie","Poland",false, List.of());
        zooservice.addPrefixToName(zoo,"To");
        assertThat(zoo.getName()).isEqualTo("ToGdanskie");
    }
    @Test
    void closeIfUnlucky(){
        Zoo zoo = new Zoo(1,"Gdanskie","Poland",false, List.of());
        zooservice.closeIfUnlucky(zoo);
        assertThat(zoo.getIsclosed()).isEqualTo(true);
    }
}
