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

    public static Integer rbdToInt(String rbdStr) {
        if(rbdStr == null || rbdStr.isEmpty()) return null;
        String cleanedRbd = rbdStr.replaceAll("[^\\d]", "");
        if (cleanedRbd.isEmpty() || cleanedRbd.length() > LEN_MAX_RBD) return null;
        return Integer.parseInt(cleanedRbd);
    }

    public static String removeSymbols(String strToClean) {
        String strCleaned = strToClean.replaceAll("(, |[^a-zA-Z0-9,;])", "");
        return strCleaned.toLowerCase();
    }

    public static String clearRut(String rut) {
        return rut.replaceAll("[^0-9k]", "");
    }

    public static boolean rutValidator(String cleanRut) {

        String rutPattern = "[0-9]{6,8}(k|[0-9])";
        String rut = Pattern.matches(rutPattern, cleanRut) ? cleanRut : null;

        if (rut == null) return false;

        String rutWithoutCheckDigit = rut.substring(0, rut.length() - 1);

        Integer numericalSeries = 0, sum = 1, rutAsInt = Integer.parseInt(rutWithoutCheckDigit);

        for (; rutAsInt != 0; rutAsInt = (int) Math.floor(rutAsInt /= 10))
            sum = (sum + rutAsInt % 10 * (9 - numericalSeries++ % 6)) % 11;

        String checkDigitVerifier = (sum > 0) ? String.valueOf(sum - 1) : "k";

        String newRut = rutWithoutCheckDigit + checkDigitVerifier;

        return (rut.equals(newRut)) ? true : false;

    }

    public static boolean emailValidator(String email) {
        String emailPattern = "^[\\w-\\.\\d]+@([\\w-\\d]+\\.[\\w]+)$";
        return (Pattern.matches(emailPattern, email)) ? true : false;
    }

}
