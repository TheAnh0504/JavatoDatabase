package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "class_id") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Class a1class;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Quan hệ n-n với đối tượng ở dưới (Teacher) (1 học sinh có nhiều giáo viên)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    @JoinTable(name = "student_teacher", //Tạo ra một join Table tên là "student_teacher"
            joinColumns = @JoinColumn(name = "student_id"),  // TRong đó, khóa ngoại chính là student_id trỏ tới class hiện tại (Student)
            inverseJoinColumns = @JoinColumn(name = "teacher_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (Teacher)
    )
    private Collection<Teacher> teachers;
}
