package plataforma1.util;

import java.io.Serializable;
import java.math.BigDecimal;

public interface Numerics extends Serializable {

    BigDecimal getOne();

    BigDecimal getTen();

    BigDecimal getHundred();

    BigDecimal getMinusOne();

    BigDecimal getZero();

    boolean isBigDecimalParsable(String string);

    boolean isIntegerParsable(String string);

    boolean isLongParsable(String str);

    boolean isEven(Integer value);

    BigDecimal parseBigDecimal(String str);

    BigDecimal safeParseBigDecimal(String str);

    Integer safeParseInteger(String str);

    BigDecimal minus(BigDecimal n1);

    BigDecimal add(BigDecimal n1, BigDecimal n2);

    BigDecimal subtract(BigDecimal n1, BigDecimal n2);

    BigDecimal multiply(BigDecimal n1, BigDecimal n2);

    BigDecimal divide(BigDecimal n1, BigDecimal n2);

    String bigDecimalToString(BigDecimal n);

}
