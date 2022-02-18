/**
 * Copyright (c) 2004-2022 QOS.ch Sarl (Switzerland)
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.slf4j.log4j12;

import org.slf4j.helpers.Util;

public class VersionUtil {
    // code was compiled under Java 8 or later
    static final int DEFAULT_GUESS = 8;

    static public int getJavaMajorVersion() {
        String javaVersionString = Util.safeGetSystemProperty("java.version");
        int result = getJavaMajorVersion(javaVersionString);
        return result;
    }

    static public int getJavaMajorVersion(String versionString) {
        if (versionString == null)
            return DEFAULT_GUESS;
        if (versionString.startsWith("1.")) {
            return versionString.charAt(2) - '0';
        } else {
            String firstDigits = extractFirstDigits(versionString);
            try {
               return Integer.parseInt(firstDigits);
            } catch(NumberFormatException e) {
                return DEFAULT_GUESS;
            }
        }
    }

    private static String extractFirstDigits(String versionString) {
        StringBuffer buf = new StringBuffer();
        for (char c : versionString.toCharArray()) {
            if (Character.isDigit(c))
                buf.append(c);
            else
                break;
        }
        return buf.toString();

    }
}
