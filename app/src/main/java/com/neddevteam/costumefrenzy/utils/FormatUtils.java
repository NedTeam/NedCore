package com.neddevteam.costumefrenzy.utils;

/**
 * Created by mcat on 7/02/15.
 */
public class FormatUtils {

    public static String hex(int n) {
        // call toUpperCase() if that's required
        return String.format("0x%8s", Integer.toHexString(n)).replace(' ', '0');
    }

}
