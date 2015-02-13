package com.neddevteam.costumefrenzy.utils;

/**
 * Created by mcat on 7/02/15.
 */
public class FormatUtils {

    /**
     * Transform an int number {@code n} to a hexadecimal number
     *
     * @param n number to be transformed
     * @return a String that contains the hexadecimal number
     */
    public static String hex(int n) {
        // call toUpperCase() if that's required
        return String.format("0x%8s", Integer.toHexString(n)).replace(' ', '0');
    }

}
