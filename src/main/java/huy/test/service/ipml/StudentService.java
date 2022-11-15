package huy.test.service.ipml;

import huy.test.model.Student;
import huy.test.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StudentService implements IStudentService{
    @Autowired
    private IStudentRepository generalRepository;
    @Override
    public Iterable<Student> findAll() {
        return generalRepository.findAll() ;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return generalRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        generalRepository.save(student);
    }

    @Override
    public void remove(Long id) {
        generalRepository.deleteById(id);
    }
}
