package br.com.tectoy.megadrivepublicity.model.dao;

import br.com.tectoy.megadrivepublicity.model.Contact;
import br.com.tectoy.megadrivepublicity.model.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDao {
    private String name;
    private String email;
    private String message;


    public Contact convertToContact(Email email){
        Contact contact = new Contact();
        contact.setName(name);
        contact.setEmail(email);
        contact.setMessage(message);
        contact.setCreationDate(Calendar.getInstance().getTime());
        return contact;
    }
}
