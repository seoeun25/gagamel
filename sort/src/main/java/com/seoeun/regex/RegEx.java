package com.seoeun.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: seoeun
 * Date: 15. 4. 12.
 * Time: 오후 2:57
 * To change this template use File | Settings | File Templates.
 */
public class RegEx {

    public RegEx() {

    }

    /**
     * Extract version in <tt>Integer</tt>.
     * @param version
     */
    public int extractVerion(String version) {
        int iVersion = 0;
        String regPattern = "(\\d).(\\d).(\\d)" ;
        Pattern pattern = Pattern.compile(regPattern);
        Matcher matcher = pattern.matcher(version);
        //System.out.println(matcher.find());
        //System.out.println(matcher.groupCount());
        if (matcher.find()) {
            System.out.println("---- find");
            for (int group = 0, count = matcher.groupCount(); group < count; group++) {
                System.out.println("groupCount : " + matcher.groupCount());
                int groupIndex = group + 1;
                String groupString = matcher.group(groupIndex);
                System.out.println("group " + groupIndex + " : " + groupString);
            }
            iVersion = Integer.parseInt(matcher.group(1) + matcher.group(2) + matcher.group(3));
        } else {
            System.out.println("---- not found");
        }
        return iVersion;
    }

    /**
     * Extract version in <tt>Integer</tt>.
     * @param version
     * @return version in <tt>Integer</tt>
     */
    public int extractVerion2(String version) {
        int iVersion = 0;
        Pattern pattern = Pattern.compile("(\\d).(\\d).(\\d)");
        Matcher matcher = pattern.matcher(version);
        //System.out.println(matcher.find());
        System.out.println(matcher.groupCount());

        if (matcher.find()) {
            iVersion = Integer.parseInt(matcher.group(1) + matcher.group(2) + matcher.group(3));
        } else {
            System.out.println("---- not found");
        }
        return iVersion;
    }
}
