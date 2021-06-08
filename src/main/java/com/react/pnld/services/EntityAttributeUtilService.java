package com.react.pnld.services;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class EntityAttributeUtilService {

    private static final int LEN_MAX_RBD = 6;

    public static String[] splitLastNames(String lastNames) {

        String[] newLastNamesArray = new String[2];

        String[] lastNamesArray = lastNames.split(" ", 2);

        newLastNamesArray[0] = lastNamesArray[0];
        newLastNamesArray[1] = (lastNamesArray.length == 1) ? null : lastNamesArray[1];

        return newLastNamesArray;
    }

    public static String normalizeRegion(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        String normalizedName = Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return normalizedName.replaceAll("region( de( la | )| del | )", "");
    }

    public static String removeAccents(String toClean) {
        if(toClean == null) return new String();
        return Normalizer.normalize(toClean, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static String removeSymbols(String strToClean) {
        String strCleaned = strToClean.replaceAll("(, |[^a-zA-Z0-9,;])", "");
        return strCleaned.toLowerCase();
    }

    public static boolean emailValidator(String email) {
        String emailPattern = "^[\\w-\\.\\d]+@([\\w-\\d]+\\.[\\w]+)$";
        return (Pattern.matches(emailPattern, email)) ? true : false;
    }

}
