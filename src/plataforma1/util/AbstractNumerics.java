package plataforma1.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public abstract class AbstractNumerics implements Numerics {

    private MathContext mathContext;
    private int precision;
    private int scale;
    private BigDecimal zero;
    private BigDecimal one;
    private BigDecimal ten;
    private BigDecimal hundred;
    private BigDecimal minusOne;

    protected void init(int precision, int scale) {
        this.precision = precision;
        this.scale = scale;

        mathContext = new MathContext(precision, RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP));

        char[] decimalChars = new char[scale];
        java.util.Arrays.fill(decimalChars, '0');
        String decimalString = new String(decimalChars);
        zero = new BigDecimal("0." + decimalString);
        one = new BigDecimal("1." + decimalString);
        ten = new BigDecimal("10." + decimalString);
        hundred = new BigDecimal("100." + decimalString);
        minusOne = new BigDecimal("-1." + decimalString);
    }

    private int getScale() {
        return scale;
    }

    @Override
    public BigDecimal getOne() {
        return one;
    }

    @Override
    public BigDecimal getTen() {
        return ten;
    }

    @Override
    public BigDecimal getHundred() {
        return hundred;
    }

    @Override
    public BigDecimal getMinusOne() {
        return minusOne;
    }

    @Override
    public BigDecimal getZero() {
        return zero;
    }

    @Override
    public boolean isBigDecimalParsable(String string) {
        try {
            parseBigDecimal(string);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    @Override
    public boolean isIntegerParsable(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    @Override
    public boolean isLongParsable(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    @Override
    public boolean isEven(Integer value) {
        return (value % 2) == 0;
    }

    @Override
    public BigDecimal parseBigDecimal(String str) {
        return new BigDecimal(str, mathContext).setScale(getScale(), mathContext.getRoundingMode());
    }

    @Override
    public BigDecimal safeParseBigDecimal(String str) {
        try {
            return parseBigDecimal(str);
        } catch (Throwable t) {
            return zero;
        }
    }

    @Override
    public Integer safeParseInteger(String str) {
        try {
            return new Integer(str);
        } catch (Throwable t) {
            return new Integer(0);
        }
    }

    @Override
    public BigDecimal minus(BigDecimal n1) {
        return multiply(n1, minusOne);
    }

    @Override
    public BigDecimal add(BigDecimal n1, BigDecimal n2) {
        return n1.add(n2, mathContext).setScale(getScale());
    }

    @Override
    public BigDecimal subtract(BigDecimal n1, BigDecimal n2) {
        return n1.subtract(n2, mathContext).setScale(getScale());
    }

    @Override
    public BigDecimal multiply(BigDecimal n1, BigDecimal n2) {
        return n1.multiply(n2, mathContext).setScale(getScale(), mathContext.getRoundingMode());
    }

    @Override
    public BigDecimal divide(BigDecimal n1, BigDecimal n2) {
        return n1.divide(n2, mathContext).setScale(getScale(), mathContext.getRoundingMode());
    }

    @Override
    public String bigDecimalToString(BigDecimal n) {
        return n.toString();
    }

}

