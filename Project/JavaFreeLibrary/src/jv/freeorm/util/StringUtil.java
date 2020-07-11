package jv.freeorm.util;

/**
 *
 * @author Mustafa SACLI
 */
public class StringUtil {

    public static String toStr(Object obj) {
        String result = "";

        if (obj != null) {

            result = String.valueOf(obj);

            if (result == null) {
                result = "";
            }
        }

        return result;
    }

    public static String trimStart(String str) {
        String rslt = StringUtil.trimStart(str, ' ');
        return rslt;
    }

    public static String trimStart(String str, char ch) {
        if (str == null) {
            return str;
        }

        if (str.length() == 0) {
            return str;
        }

        String result = "";
        try {
            result = str;
            String chk = String.valueOf(ch);
            String tmp = result.substring(0, 1);

            if (chk.equals(tmp)) {
                //if (result.length() == 1) {
                //     result = "";
                //} else {
                result = result.substring(1);
                //}

                result = StringUtil.trimStart(result, ch);
            }
        } catch (Exception e) {
        }

        return result;
    }

    public static String trimEnd(String str) {
        String rslt = trimEnd(str, ' ');
        return rslt;
    }

    public static String trimEnd(String str, char ch) {

        if (str == null) {
            return str;
        }

        if (str.length() == 0) {
            return str;
        }

        String result = "";

        try {
            result = str;
            String chk = String.valueOf(ch);
            String tmp = result.substring(result.length() - 1);

            if (chk.equals(tmp)) {
                result = result.substring(0, result.length() - 1);
                result = trimEnd(result, ch);
            }

        } catch (Exception e) {
        }

        return result;
    }

    public static boolean isValidString(String str) {
        boolean result = false;

        if (str != null) {
            result = str.trim().length() > 0;
        }

        return result;
    }

    public static boolean isNullOrEmpty(String str) {
        boolean result = true;

        if (str != null) {
            result = str.length() == 0;
        }

        return result;
    }

    public static boolean isNullOrWhiteSpace(String str) {
        boolean result = true;

        if (str != null) {
            String s = str;
            result = s.trim().length() == 0;
        }

        return result;
    }

    public static boolean Matches(String str1, String str2) {
        boolean is_match = false;

        if (str1 == null && str2 == null) {
            is_match = true;
        }

        if (str1 != null && str2 != null) {
            if (str1.length() == str2.length()) {
                int len = str1.length();
                boolean _state = true;
                char[] ch1 = str1.toCharArray();
                char[] ch2 = str2.toCharArray();
                for (int i = 0; i < len; i++) {
                    _state = ch1[i] == ch2[i];
                    if (_state == false) {
                        break;
                    }
                }

                is_match = _state;
            }
        }

        return is_match;
    }

    public static String newLine() {
        return System.getProperty("line.separator");
    }

    public static String concat(String... strObjs) {
        String s = "";

        if (strObjs != null) {
            for (String strObj : strObjs) {
                if (!isNullOrEmpty(strObj)) {
                    s = s.concat(strObj);
                }
            }
        }

        return s;
    }
}
