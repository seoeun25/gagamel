package com.seoeun.homework.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PalindromeApp {

    public static String CURSUR = "PalindromeApp> ";

    public static String USAGE = "Please type the number to convert.";

    public static enum COMMAND {
        quit(),
        exit();
    }


    public static void main(String[] args) {
        PalindromeApp app = new PalindromeApp();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            System.out.print(CURSUR + USAGE + "\n" + CURSUR);
            String line = reader.readLine();

            int result = 0;
            while (line != null) {
                result = app.doCommand(line);
                if (result != 0) {
                    break;
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(" bye ......");
            try {
                reader.close();
            } catch (IOException ie) {

            }
            System.exit(0);
        }
    }

    public int doCommand(String command) {
        if (command.startsWith(COMMAND.exit.name()) || command.startsWith(COMMAND.quit.name())) {
            return 1;
        }
        try {
            int number = Integer.parseInt(command);
            convertPalindrome(number);
        } catch (NumberFormatException e) {
            System.out.print(USAGE + "\n" + CURSUR);
        }
        return 0;
    }

    public void convertPalindrome(int a) {
        System.out.print(" " + getHigherPalindrome(a) + "\n" + CURSUR);
    }

    public boolean isPalindrome(String str) {

        int start = 0;
        int end = str.length() - 1;
        int half = str.length() / 2;

        boolean result = true;
        for (int i = 0; i < half; i++, start++, end--) {
            if (str.charAt(start) != str.charAt(end)) {
                result = false;
            }
        }

        return result;
    }

    public int getHigherPalindrome(int input) {
        String str = String.valueOf(input);
        char[] chars = str.toCharArray();

        int half = (int) Math.ceil((double) chars.length / 2);
        boolean isEven = (chars.length % 2 == 0);
        int lhalf = half - 1;
        int rhalf = isEven ? half : lhalf;
        int lc = lhalf;
        int rc = rhalf;
        boolean pass = false;
        for (int loop = 0; loop < half; loop++, lc--, rc++) {
            if (toInt(chars[lc]) < toInt(chars[rc])) {
                if (pass) {
                    int plus = toInt(chars[lc + 1]) + 1;
                    chars[lc + 1] = toChar(plus);
                    chars[rc - 1] = toChar(plus);
                } else {
                    chars[lc] = chars[rc];
                }
                copyLeftToRight(half - loop, chars);
                pass = false;
                break;

            } else if (toInt(chars[lc]) > toInt(chars[rc])) {
                if (!pass) {
                    chars[rc] = chars[lc];
                }
                copyLeftToRight(half - loop, chars);
                pass = false;
                break;
            } else {
                pass = true;
            }
        }

        if (pass) {
            int plus = toInt(chars[lhalf]) + 1;
            chars[lhalf] = toChar(plus);
            chars[rhalf] = toChar(plus);
        }

        return toInt(chars);
    }

    public int getHigherPalindrome_org(int input) {
        String str = String.valueOf(input);
        char[] chars = str.toCharArray();

        int half = (int) Math.ceil((double) str.length() / 2);
        boolean isEven = (str.length() % 2 == 0);
        int lhalf = half - 1;
        int rhalf = isEven ? half : lhalf;
        int lc = lhalf;
        int rc = rhalf;
        boolean pass = false;
        for (int loop = 0; loop < half; loop++, lc--, rc++) {
            if (toInt(str.charAt(lc)) < toInt(str.charAt(rc))) {
                if (pass) {
                    int plus = toInt(chars[lc + 1]) + 1;
                    chars[lc + 1] = toChar(plus);
                    chars[rc - 1] = toChar(plus);
                } else {
                    chars[lc] = str.charAt(rc);
                }
                copyLeftToRight(half - loop, chars);
                pass = false;
                break;

            } else if (toInt(str.charAt(lc)) > toInt(str.charAt(rc))) {
                if (pass) {
                } else {
                    chars[rc] = str.charAt(lc);
                }
                copyLeftToRight(half - loop, chars);
                pass = false;
                break;
            } else {
                pass = true;
            }

        }

        if (pass) {
            int plus = toInt(chars[lhalf]) + 1;
            chars[lhalf] = toChar(plus);
            chars[rhalf] = toChar(plus);
        }

        return toInt(chars);
    }

    private void copyLeftToRight(int limit, char[] chars) {
        int e = chars.length - 1;
        for (int i = 0; i < limit; i++, e--) {
            chars[e] = chars[i];
        }
    }

    private void printChars(char[] chars) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            builder.append(chars[i]);
        }
        System.out.println(builder.toString());
    }

    public char toChar(int iValue) {
        char ch = Character.forDigit(iValue, 10);
        return ch;
    }

    public int toInt(char a) {
        return Character.getNumericValue(a);
    }

    public int toInt(char[] chars) {
        String a = new String(chars);
        return Integer.parseInt(a);
    }

}
