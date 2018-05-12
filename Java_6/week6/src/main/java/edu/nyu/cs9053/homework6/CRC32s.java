package edu.nyu.cs9053.homework6;

import java.util.zip.CRC32;

/**
 * User: blangel
 *
 * Unnecessary to modify this file, so do not.
 *
 */
class CRC32s {

    public static long crc32(String value) {
        CRC32 crc32 = new CRC32();
        crc32.update(value.getBytes());
        return crc32.getValue();
    }


    private CRC32s() { }

}
