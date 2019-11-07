package plataforma1.util;

import plataforma1.services.ValidationException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Pattern;

public class ValidationUtils {

    private static Pattern integerPattern = Pattern.compile("\\d+");

    public static boolean validateCuit(String cuit) {
        String[] componentesCuit = cuit.split("-");
        if (componentesCuit.length != 3) {
            return false;
        }
        if (componentesCuit[0].length() != 2) {
            return false;
        }
        if (componentesCuit[1].length() < 6 || componentesCuit[1].length() > 8) {
            return false;
        }
        if (componentesCuit[2].length() != 1) {
            return false;
        }
        for (String input : componentesCuit) {
            if (!integerPattern.matcher(input.trim()).matches()) {
                return false;
            }
        }
        return true;
    }

    public static void validatePositiveInteger(String name, Integer value, ValidationException validationException) {
        if (value == null) {
            validationException.addMessage("Debe ingresar un valor para " + name);
        } else if (value < 1) {
            validationException.addMessage("el valor de " + name + " debe ser positivo");
        }
    }

    public static void validatePositiveBigDecimal(String name, BigDecimal value, ValidationException validationException) {
        if (value == null) {
            validationException.addMessage("Debe ingresar un valor para " + name);
        } else if (value.compareTo(BigDecimal.ZERO) < 1) {
            validationException.addMessage("el valor de " + name + " debe ser positivo");
        }
    }

    public static void validatePositiveLong(String name, Long value, ValidationException validationException) {
        if (value == null) {
            validationException.addMessage("Debe ingresar un valor para " + name);
        } else if (value < 1) {
            validationException.addMessage("el valor de " + name + " debe ser positivo");
        }
    }

    public static void validateNonEmptyString(String name, String value, ValidationException validationException, int maxLen) {
        if (value == null || value.trim().isEmpty()) {
            validationException.addMessage("Debe ingresar un valor para " + name);
        } else if (value.length() > maxLen) {
            validationException.addMessage("El valor de " + name + " es demasiado extenso");
        }
    }

    public static void validateActualYear(String name, Integer value, ValidationException validationException) {
        if (value == null) {
            validationException.addMessage("Debe ingresar un valor para " + name);
        } else if (value < 1) {
            validationException.addMessage("El valor de " + name + " debe ser positivo");
        } else if (!DateTimeUtils.isActualYear(value)) {
            validationException.addMessage("El valor de " + name + " debe ser menor");
        } else if (value < 1900) {
            validationException.addMessage("El valor de " + name + " debe ser posterior al año 1900");
        }
    }

    public static void validateActualDate(String name, Date value, ValidationException validationException) {
        if (value == null) {
            validationException.addMessage("Debe ingresar un valor para " + name);
        } else if (DateTimeUtils.isFutureDate(value)) {
            validationException.addMessage("El valor de " + name + " debe ser menor");
        }
    }

    public static void validateNotNull(String name, Object object, ValidationException validationException) {
        if (object == null) {
            validationException.addMessage("Debe ingresar un valor para " + name);
        }
    }

    public static void validateNonEmptyString(String name, String value) {
        ValidationException validationException = new ValidationException();
        validateNonEmptyString(name, value, validationException, 255);
        if (!validationException.getMessageList().isEmpty()) {
            throw validationException;
        }
    }

    public static void validateMonth(String name, Integer value, ValidationException validationException) {
        if (value == null) {
            validationException.addMessage("Debe ingresar un valor para " + name);
        } else if (value < 1) {
            validationException.addMessage("El valor de " + name + " debe ser positivo");
        } else if (value > 12) {
            validationException.addMessage("El valor de " + name + " debe ser menor");
        }
    }

    public static void validateDay(String name, Integer value, ValidationException validationException) {
        if (value == null) {
            validationException.addMessage("Debe ingresar un valor para " + name);
        } else if (value < 1) {
            validationException.addMessage("El valor de " + name + " debe ser positivo");
        } else if (value > 31) {
            validationException.addMessage("El valor de " + name + " debe ser menor");
        }
    }

    public static void validateEmail(String name, String value, ValidationException validationException) {
        if (value == null || value.isEmpty()) {
            validationException.addMessage("Debe ingresar " + name);
        } else if (value.length() > 255) {
            validationException.addMessage("El valor de " + name + " es demasiado extenso");
        } else {
            int atIndex = value.indexOf('@');
            int dotIndex = value.lastIndexOf('.');
            if (atIndex < 0 || dotIndex < atIndex) {
                validationException.addMessage("El formato de " + name + " no es válido");
            }
        }
    }

    public static void validateNumeroDocumento(String name, String value, ValidationException validationException) {
        if (value == null || value.isEmpty()) {
            validationException.addMessage("Debe ingresar " + name);
        } else if (value.length() > 8) {
            validationException.addMessage("El valor de " + name + " no debe exceder 8 dígitos");
        }
    }
}
