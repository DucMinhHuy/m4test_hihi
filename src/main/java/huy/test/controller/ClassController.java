package huy.test.controller;

import huy.test.model.Classroom;
import huy.test.service.ipml.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RequestMapping("/class")
public class ClassController {
@Autowired
    private IClassService classService;
    @GetMapping
    public ResponseEntity<Iterable<Classroom>> findAllclass() {
        List<Classroom> classrooms = (List<Classroom>) classService.findAll();
        if (classrooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(classrooms, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public ResponseEntity<Classroom> findTrainerById(@PathVariable Long id){
        Optional<Classroom> classOptional=classService.findById(id);
        if(!classOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(classOptional.get(),HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.PUT,value = "edit/{id}")
    public ResponseEntity<Classroom> editIncome(@PathVariable Long id, @RequestBody Classroom classroom) {
        Optional<Classroom> classIncomeOptional = classService.findById(id);
        if (!classIncomeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        classroom.setId(id);
        classService.save(classroom);
        return new ResponseEntity<>(classroom, HttpStatus.OK);
    }
    @RequestMapping(method =RequestMethod.DELETE,value = "/{id}")
    public ResponseEntity<Classroom> deleteTrainer(@PathVariable Long id){
        Optional<Classroom> trainerOptional=classService.findById(id);
        if(!trainerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        classService.remove(id);
        return new ResponseEntity<>(trainerOptional.get(),HttpStatus.NO_CONTENT);
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/createroom");
        modelAndView.addObject("class", new Classroom());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveClass(@ModelAttribute("student") Classroom classroom) {
        classService.save(classroom);
        ModelAndView modelAndView = new ModelAndView("/createroom");
        modelAndView.addObject("class", new Classroom());
        modelAndView.addObject("message", "New clas created successfully");
        return modelAndView;
    }
}
