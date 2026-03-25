package SpringJPA.H2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StuService {

    @Autowired
    StuRepo sturepo;

    public StudentEntity createUser(StudentEntity student) {
        sturepo.save(student);
        return student;
    }

    public List<StudentEntity> getUser() {
        return sturepo.findAll();

    }


    public Optional<StudentEntity> getById(Long id) {
      return sturepo.findById(id);
    }

    public void deleteAll() {
        sturepo.deleteAll();
    }

    public boolean deleteById(Long id) {
        if(sturepo.existsById(id)){
            sturepo.deleteById(id);
            return true;
        }
        return false;
    }


    public StudentEntity updateUser(StudentEntity student, Long id) {
        StudentEntity value = sturepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));

        value.setName(student.getName());

        return sturepo.save(value);





    }
    
}
