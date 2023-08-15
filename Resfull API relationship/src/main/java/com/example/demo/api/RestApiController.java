package com.example.demo.api;

import com.example.demo.model.Book;
import com.example.demo.model.Class;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {
    public static Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    ClassRepository classRepository;

    /*1. lấy tất cả liên hệ
     *   ở đây nó sẽ gọi method findAll() của JpaRepository trả về toàn bộ danh
     *  sách liên hệ có trong database method: khai báo phương thức khi call url, GET, PUT, PATCH, POST,....*/
    //@RequestMapping(value = "/class", method = RequestMethod.GET)
    @GetMapping("/class")
    public ResponseEntity<List<Class>> listAllClass(){
        List<Class> listClass= classRepository.findAll();
        if(listClass.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Class>>(listClass, HttpStatus.OK);
    }

    /*2. lấy một liên hệ
     *   @PathVariable mapping biến ở đường dẫn với biến tham số gọi method getOne()
     *  để lấy dữ liệu thông qua đối số*/
    @GetMapping("/class/{id}")
    public Class findClass(@PathVariable("id") long id) {
        Class class1 = classRepository.getOne(id);
        if(class1 == null) {
            ResponseEntity.notFound().build();
        }
        return class1;
    }

    /*3. tạo mới liên hệ
     * @RequestBody tương ứng với body của request @Valid để đảm bảo request body
     *  là hợp lệ nếu ta dùng các annotation validation như @NotBlank...*/
    @PostMapping("/class")
    public Class saveClass(@Valid @RequestBody Class class1) {
        return classRepository.save(class1);
    }

    /*4. update liên hệ*/
    @PutMapping("/class/{id}")
    public ResponseEntity<Class> updateClass(@PathVariable(value = "id") long classId,
                                             @Valid @RequestBody Class classForm) {
        Class class1 = classRepository.getOne(classId);
        if(class1 == null) {
            return ResponseEntity.notFound().build();
        }
        class1.setName(classForm.getName());

        Class updatedClass = classRepository.save(class1);
        return ResponseEntity.ok(updatedClass);
    }

    /*5. Xóa liên hệ*/
    @DeleteMapping("/class/{id}")
    public ResponseEntity<Class> deleteClass(@PathVariable(value = "id") Long id) {
        Class class1 = classRepository.getOne(id);
        if(class1 == null) {
            return ResponseEntity.notFound().build();
        }

        classRepository.delete(class1);
        return ResponseEntity.ok().build();
    }

    @Autowired
    StudentRepository studentRepository;

    //@RequestMapping(value = "/student", method = RequestMethod.GET)
    @GetMapping("/student")
    public ResponseEntity<List<Student>> listAllStudent(){
        List<Student> listStudent = studentRepository.findAll();
        if(listStudent.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Student>>(listStudent, HttpStatus.OK);
    }

    @Autowired
    TeacherRepository teacherRepository;

    //@RequestMapping(value = "/teacher", method = RequestMethod.GET)
    @GetMapping("/teacher")
    public ResponseEntity<List<Teacher>> listAllTeacher(){
        List<Teacher> listTeacher = teacherRepository.findAll();
        if(listTeacher.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Teacher>>(listTeacher, HttpStatus.OK);
    }

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/book")
    public ResponseEntity<List<Book>> listAllBook(){
        List<Book> listBook = bookRepository.findAll();
        if(listBook.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Book>>(listBook, HttpStatus.OK);
    }
}
