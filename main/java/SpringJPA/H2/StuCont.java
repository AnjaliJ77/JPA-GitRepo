package SpringJPA.H2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.values;

@RestController
@RequestMapping("/student")
public class StuCont {

    @Autowired
    StuService stuser;


//    @PostMapping("/create")
//    StudentEntity createUser(@RequestBody StudentEntity student){
//        return stuser.createUser(student);
//
//    }

    @PostMapping("/create")
    ResponseEntity<String> createUser(@RequestBody StudentEntity student){
        stuser.createUser(student);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }


//    @GetMapping("/getuser")
//    List<StudentEntity> getUser(){
//       return stuser.getUser();
//    }

    @GetMapping("/getuser")
    ResponseEntity<List<StudentEntity>> getUser(){
        return ResponseEntity.ok(stuser.getUser());

    }

    @GetMapping("byid/{id}")
    ResponseEntity<StudentEntity> getById(@PathVariable Long id){
        return stuser.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
@DeleteMapping("/delete")
    ResponseEntity<String> deleteAll(){
        stuser.deleteAll();
        return new ResponseEntity<>("Deleted successfully", HttpStatus.GONE);
}

@DeleteMapping("/deletee/{id}")
    ResponseEntity<StudentEntity> deletById(@PathVariable Long id){
        boolean isdeleted=stuser.deleteById(id);
        if(isdeleted){
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.notFound().build();
}

}
