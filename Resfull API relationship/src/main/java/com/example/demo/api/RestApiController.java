package com.example.demo.api;

import com.example.demo.model.Class;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {
    public static Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    ClassRepository classRepository;

    /*lấy tất cả liên hệ
     *   ở đây nó sẽ gọi method findAll() của JpaRepository trả về toàn bộ danh
     *  sách liên hệ có trong database method: khai báo phương thức khi call url, GET, PUT, PATCH, POST,....*/
    @RequestMapping(value = "/class", method = RequestMethod.GET)
    public ResponseEntity<List<Class>> listAllClass(){
        List<Class> listClass= classRepository.findAll();
        if(listClass.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Class>>(listClass, HttpStatus.OK);
    }

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listAllStudent(){
        List<Student> listStudent = studentRepository.findAll();
        if(listStudent.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Student>>(listStudent, HttpStatus.OK);
    }

    @Autowired
    TeacherRepository teacherRepository;

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public ResponseEntity<List<Teacher>> listAllTeacher(){
        List<Teacher> listTeacher = teacherRepository.findAll();
        if(listTeacher.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Teacher>>(listTeacher, HttpStatus.OK);
    }
}
