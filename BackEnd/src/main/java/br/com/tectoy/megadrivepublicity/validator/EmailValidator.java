package br.com.tectoy.megadrivepublicity.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private static final String INVALID_CHARACTER_LIST = "[^A-Za-z0-9.\\-_@ ]";

    private EmailValidator(){}

    public static boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(INVALID_CHARACTER_LIST);
        Matcher matcher = pattern.matcher(email);
        return !matcher.find() && TextValidator.isTextNotBlank(email);
    }

}
