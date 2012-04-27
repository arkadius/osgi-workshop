/*
 * Copyright (c) 2012. TouK sp. z o.o. s.k.a.
 * All rights reserved
 */
package pl.touk.osgiworkshop.game;

import com.google.common.base.Strings;

/**
 * @author arkadius
 */
public class TextNormalizer {

    public static String normalize(String str) {
        return translate(str.toLowerCase(), "ąćęłńóśźż,.?!:-", "acelnoszz      ");
    }

    private static String translate(String str, String searchChars, String replaceChars) {
        if (Strings.isNullOrEmpty(str)) {
            return str;
        }
        StringBuffer buffer = new StringBuffer(str.length());
        char[] chrs = str.toCharArray();
        char[] withChrs = replaceChars.toCharArray();
        int sz = chrs.length;
        int withMax = replaceChars.length() - 1;
        for(int i=0; i<sz; i++) {
            int idx = searchChars.indexOf(chrs[i]);
            if(idx != -1) {
                if(idx > withMax) {
                    idx = withMax;
                }
                buffer.append(withChrs[idx]);
            } else {
                buffer.append(chrs[i]);
            }
        }
        return buffer.toString();
    }
}
