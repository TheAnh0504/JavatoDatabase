package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "class_id") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Class a1class;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Quan hệ n-n với đối tượng ở dưới (Book) (1 học sinh có nhiều sách)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    @JoinTable(name = "student_book", //Tạo ra một join Table tên là "student_teacher"
            joinColumns = @JoinColumn(name = "student_id"),  // TRong đó, khóa ngoại chính là student_id trỏ tới class hiện tại (Student)
            inverseJoinColumns = @JoinColumn(name = "book_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (Teacher)
    )
    private Collection<Book> books;

}
