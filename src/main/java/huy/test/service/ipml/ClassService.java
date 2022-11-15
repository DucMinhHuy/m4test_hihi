package huy.test.service.ipml;

import huy.test.model.Classroom;
import huy.test.repository.IClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassService implements IClassService{
    @Autowired
    private IClassRepository classRepository;
    @Override
    public Iterable<Classroom> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Classroom> findById(Long id) {
        return classRepository.findById(id);
    }

    @Override
    public void save(Classroom classroom) {
classRepository.save(classroom);
    }

    @Override
    public void remove(Long id) {
classRepository.deleteById(id);
    }
}
