package br.com.tectoy.megadrivepublicity.validator;

public class TextValidator {

    private static final String INVALID_CHARACTER_LIST = "[^A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ0-9.\\-_ ]";

    private TextValidator(){}

    public static String sanitaze(String text){
        return text.replaceAll(INVALID_CHARACTER_LIST, "");
    }

    public static boolean isValidText(String text){
        return !text.matches(INVALID_CHARACTER_LIST) && isTextNotBlank(text);
    }

    public static boolean isTextNotBlank(String text){
        return text != null && !text.isBlank();
    }
}
