package com.example.demo.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;

@RestController
@RequestMapping("/api")
public class RestApiController {
    public static Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    ContactService contactService;

    /*lấy tất cả liên hệ
    *   ở đây nó sẽ gọi method findAll() của JpaRepository trả về toàn bộ danh
    *  sách liên hệ có trong database method: khai báo phương thức khi call url, GET, PUT, PATCH, POST,....*/
    @RequestMapping(value = "/contact/", method = RequestMethod.GET)
    public ResponseEntity<List<Contact>> listAllContact(){
        List<Contact> listContact= contactService.findAll();
        if(listContact.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Contact>>(listContact, HttpStatus.OK);
    }

    /*lấy một liên hệ
    *   @PathVariable mapping biến ở đường dẫn với biến tham số gọi method getOne()
    *  để lấy dữ liệu thông qua đối số*/
    @RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
    public Contact findContact(@PathVariable("id") long id) {
        Contact contact= contactService.getOne(id);
        if(contact == null) {
            ResponseEntity.notFound().build();
        }
        return contact;
    }

    /*tạo mới liên hệ
    * @RequestBody tương ứng với body của request @Valid để đảm bảo request body
    *  là hợp lệ nếu ta dùng các annotation validation như @NotBlank...*/
    @RequestMapping(value = "/contact/", method = RequestMethod.POST)
    public Contact saveContact(@Valid @RequestBody Contact contact) {
        return contactService.save(contact);
    }

    /*update liên hệ*/
    @RequestMapping(value = "/contact/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Contact> updateContact(@PathVariable(value = "id") Long contactId,
                                                 @Valid @RequestBody Contact contactForm) {
        Contact contact = contactService.getOne(contactId);
        if(contact == null) {
            return ResponseEntity.notFound().build();
        }
        contact.setName(contactForm.getName());
        contact.setAge(contactForm.getAge());
        contact.setEmail(contactForm.getEmail());
        contact.setDob(contactForm.getDob());

        Contact updatedContact = contactService.save(contact);
        return ResponseEntity.ok(updatedContact);
    }

    /*Xóa liên hệ*/
    @RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Contact> deleteContact(@PathVariable(value = "id") Long id) {
        Contact contact = contactService.getOne(id);
        if(contact == null) {
            return ResponseEntity.notFound().build();
        }

        contactService.delete(contact);
        return ResponseEntity.ok().build();
    }
}

