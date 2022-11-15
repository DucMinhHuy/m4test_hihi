package huy.test.repository;

import huy.test.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends CrudRepository<Student, Long> {
}
