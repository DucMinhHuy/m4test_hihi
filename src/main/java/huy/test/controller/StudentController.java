package huy.test.controller;

import huy.test.model.Student;
import huy.test.service.ipml.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
//    @GetMapping
//    public ResponseEntity<Iterable<Student>> findAllStudent() {
//        List<Student> students = (List<Student>) studentService.findAll();
//        if (students.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(students, HttpStatus.OK);
//    }
@GetMapping
public ModelAndView listCustomers() {
    ModelAndView modelAndView = new ModelAndView("/list");
    modelAndView.addObject("student", studentService.findAll());
    return modelAndView;
}
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public ResponseEntity<Student> findTrainerById(@PathVariable Long id){
        Optional<Student> trainerOptional=studentService.findById(id);
        if(!trainerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainerOptional.get(),HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.PUT,value = "edit{id}")
    public ResponseEntity<Student> editIncome(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> trainerIncomeOptional = studentService.findById(id);
        if (!trainerIncomeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        student.setId(id);
        studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @RequestMapping(method =RequestMethod.DELETE,value = "/{id}")
    public ResponseEntity<Student> deleteTrainer(@PathVariable Long id){
        Optional<Student> trainerOptional=studentService.findById(id);
        if(!trainerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.remove(id);
        return new ResponseEntity<>(trainerOptional.get(),HttpStatus.NO_CONTENT);
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCustomer(@ModelAttribute("student") Student student) {
        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customer", new Student());
        modelAndView.addObject("message", "New student created successfully");
        return modelAndView;
    }
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Student> createTrainer(@RequestBody Student student){
//        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
//    }
}
