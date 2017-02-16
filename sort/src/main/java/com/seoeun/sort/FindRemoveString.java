package com.seoeun.sort;

/**
 * 특정 문자열이 주어졌을 때, 특정 단어를 찾아 업애기
 * @author seoeun
 * @since ${VERSION} on 2/17/17
 */
public class FindRemoveString {

    /**
     * 특정 문자열에서 특정 단어 없애기
     * @param given
     * @param str
     * @return
     */
    public String findAndRemove(String given, String str) {
        StringBuilder builder = new StringBuilder();

        String origin = given;
        int index = 0;
        while(origin.indexOf(str) != -1) {
            index = origin.indexOf(str);
            builder.append(origin.substring(0, index));
            origin = origin.substring(index + str.length());
        }
        if (origin.length() != 0) {
            builder.append(origin);
        }

        return builder.toString();
    }

    public String findAndReplace(String given, String str, String replace) {
        StringBuilder builder = new StringBuilder();

        String origin = given;
        int index = 0;
        while (origin.indexOf(str) != -1) {
            index = origin.indexOf(str);
            builder.append(origin.substring(0, index));
            builder.append(replace);
            origin = origin.substring(index +str.length());
        }

        if (origin.length() != 0) {
            builder.append(origin);
        }

        return builder.toString();
    }
}
