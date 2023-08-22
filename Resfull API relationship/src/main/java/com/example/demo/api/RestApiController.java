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
    /*Or
    *@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}*/

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
    /*Or
    * @GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}*/

    /*3. tạo mới liên hệ
     * @RequestBody tương ứng với body của request @Valid để đảm bảo request body
     *  là hợp lệ nếu ta dùng các annotation validation như @NotBlank...*/
    @PostMapping("/class")
    public Class saveClass(@Valid @RequestBody Class class1) {
        return classRepository.save(class1);
    }
    /*Or
    *@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	} */

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
    /*Or
    * @PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());

		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}*/

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
    /*Or
    * @DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}*/

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
