package pl.pjatk.zoo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zoo")
public class ZooRestController {

    private final ZooService zooService;

    public ZooRestController(ZooService zooService) {
        this.zooService = zooService;
    }

    @GetMapping("/example")
    public ResponseEntity<Zoo> getExampleZoo() {
        return ResponseEntity.ok(zooService.getExampleZoo());
    }

    @GetMapping("/empty")
    public ResponseEntity<Zoo> getEmptyZoo() {
        return ResponseEntity.ok(zooService.getEmptyZoo());
    }

    @GetMapping("/named")
    public ResponseEntity<Zoo> getEmptyNamedZoo() {
        return ResponseEntity.ok(zooService.getEmptyNamedZoo("name"));
    }

    @GetMapping("/zoos")
    public ResponseEntity<List<Zoo>> getAllZoo() { return ResponseEntity.ok(zooService.getListZoo()); }
}

