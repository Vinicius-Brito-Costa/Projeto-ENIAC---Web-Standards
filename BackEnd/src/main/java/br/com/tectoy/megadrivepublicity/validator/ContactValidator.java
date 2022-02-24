package br.com.tectoy.megadrivepublicity.validator;

import br.com.tectoy.megadrivepublicity.model.dao.ContactDao;

public class ContactValidator {

    private ContactValidator(){}

    public static boolean validateContact(ContactDao contact){

        if(!EmailValidator.isValidEmail(contact.getEmail())){
            return false;
        }

        if(!TextValidator.isValidText(contact.getName())){
            contact.setName(TextValidator.sanitaze(contact.getName()));
            if(!TextValidator.isValidText(contact.getName())){
                return false;
            }
        }

        if(!TextValidator.isValidText(contact.getMessage())){
            contact.setMessage(TextValidator.sanitaze(contact.getMessage()));
            if(!TextValidator.isValidText(contact.getMessage())){
                return false;
            }
        }
        return true;
    }
}
