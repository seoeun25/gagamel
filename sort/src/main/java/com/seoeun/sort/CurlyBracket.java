package com.seoeun.sort;

public class CurlyBracket {

    public CurlyBracket() {

    }

    public boolean isClosed(String str) {
        return getStatus(str) == 0;
    }


    public int getStatus(String str) {
        int status = 0;
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{') {
                status++;
            } else if (chars[i] == '}') {
                status--;
            }
        }
        return status;
    }
}
