package jv.freeorm.util;

import java.security.InvalidParameterException;

/**
 *
 * @author Mustafa SACLI
 */
public class MathUtil {

    public static int Convert2Int(String willBeConverted) {
        try {

            return Integer.parseInt(willBeConverted);
        } catch (Exception e) {
            return 0;
        }
    }

    public static int Convert2Int(Object willBeConverted) {
        try {
            return Integer.parseInt(willBeConverted.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public static long Convert2Long(String willBeConverted) {
        try {
            return Long.parseLong(willBeConverted);
        } catch (Exception e) {
            return 0;
        }
    }

    public static long Convert2Long(Object willBeConverted) {
        try {
            return Long.parseLong(willBeConverted.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    public static double Convert2Double(String willBeConverted) {
        try {
            return Double.parseDouble(willBeConverted);
        } catch (Exception e) {
            return 0.0d;
        }
    }

    public static double Convert2Double(Object willBeConverted) {
        try {
            return Double.parseDouble(willBeConverted.toString());
        } catch (Exception e) {
            return 0.0d;
        }
    }

    public static String GetIntegerBits(Integer sayi) {
        if (sayi < 0) {
            throw new InvalidParameterException("Number is greater than zero.");
        } else {
            switch (sayi) {
                case 0:
                    return "0";
                case 1:
                    return "1";
                default: {
                    Integer sy = sayi - (sayi / 2) * 2;
                    return GetIntegerBits(sayi / 2).concat(sy.toString());
                }

            }
        }
    }

    public static String GetLongBits(Long sayi) {
        if (sayi < 0) {
            throw new InvalidParameterException("Number is greater than zero.");
        } else {

            if (sayi == 0) {
                return "0";
            } else if (sayi == 1) {
                return "1";
            } else {
                Long sy = sayi - (sayi / 2) * 2;
                return GetLongBits(sayi / 2).concat(sy.toString());
            }
        }
    }

    public static int[] getPascalTriangle(int power) {
        if (power < 0) {
            throw new IllegalArgumentException("Üs sıfırdan küçük olamaz...");
        } else {
            switch (power) {
                case 0:
                    return new int[]{1};
                case 1:
                    return new int[]{1, 1};
                default:
                    int[] retArray = new int[power + 1];
                    retArray[0] = 1;
                    retArray[power] = 1;
                    int[] tmpArray = getPascalTriangle(power - 1);
                    for (int i = 1; i < power; i++) {
                        retArray[i] = tmpArray[i] + tmpArray[i - 1];
                    }//end for
                    return retArray;
            }//end switch
        }//end else
    } //end getPascalTriangle

}
