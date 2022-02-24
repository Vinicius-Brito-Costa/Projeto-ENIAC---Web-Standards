package br.com.tectoy.megadrivepublicity.model;

import java.util.Calendar;
import java.util.Date;

import br.com.tectoy.megadrivepublicity.model.dao.ContactDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToOne
    private Email email;
    private String message;
    private Date creationDate;


    public Contact(ContactDao contactDao, Email email){
        this.setName(contactDao.getName());
        this.setEmail(email);
        this.setMessage(contactDao.getMessage());
        this.setCreationDate(Calendar.getInstance().getTime());
    }

}
