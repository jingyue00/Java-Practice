package edu.nyu.cs9053.homework2;

import edu.nyu.cs9053.homework2.model.DiagnosticTroubleCode;

/**
 * User: blangel
 */
public class Mechanic {

    /**
     * @see {@literal https://en.wikipedia.org/wiki/OBD-II_PIDs#Mode_3_(no_PID_required)}
     * @implNote For simplification of this homework, contrary to the Wikipedia article {@code data} is not in the ISO 15765-2 protocol.
     *           It is simply an array of data where the length should be equal to {@code expectedAmount} times 2.
     * @implNote If {code data} is null, empty or not equal to {@code expectedAmount} times 2 then throw
     *           an {@linkplain IllegalArgumentException}; i.e. {@code throw new IllegalArgumentException}
     * @param data to parse
     * @param expectedAmount of {@linkplain DiagnosticTroubleCode} to decode
     * @return the decoded {@linkplain DiagnosticTroubleCode} objects see {@linkplain DiagnosticTroubleCode#construct(String)} to create the object.
     */
    public static DiagnosticTroubleCode[] decode(byte[] data, int expectedAmount) {
        if (data == null || data.length <= 0 || data.length != (expectedAmount * 2)) {
            throw new IllegalArgumentException("data is invalid!");
        }
        DiagnosticTroubleCode[] result = new DiagnosticTroubleCode[expectedAmount];

        // For all bytes in data, use pair of bytes to parse Code.
        for (int index = 0; index < data.length; index += 2) {
            result[index / 2] = parseCode(data[index], data[index + 1]);
        }
        return result; 
    }

    /**
    * For each pair of byte in code, parse them to Object DiagnosticTroubleCode.
    * @param firstByte is the first byte in each pair of bytes from DiagnosticTroubleCode[]
    * @param secondByte is the second byte in each pair of bytes from DiagnosticTroubleCode[]
    * @return the object we get after parse the first and the second bytes.
    */
    public static DiagnosticTroubleCode parseCode(byte firstByte, byte secondByte) {
        StringBuffer sb = new StringBuffer();

        // Use the first and second bits in first byte to determine letter P,C,B,U.
        if (checkBit(firstByte, 7) && checkBit(firstByte, 6)) {
            sb.append('P');
        } else if (checkBit(firstByte, 7)) {
            sb.append('C');
        } else if (checkBit(firstByte, 6)) {
            sb.append('B');
        } else {
            sb.append('U');
        }

        // Use the third and fourth bits in first byte to determine number 0,1,2,3.
        if (checkBit(firstByte, 5) && checkBit(firstByte, 4)) {
            sb.append('0');
        } else if (checkBit(firstByte, 5)) {
            sb.append('1');
        } else if (checkBit(firstByte, 4)) {
            sb.append('2');
        } else {
            sb.append('3');
        }

        // Use last four bits in first byte and each four bits in second byte to determine three hex number.
        sb.append(byteToHex(firstByte));
        sb.append(byteToHex((byte)(secondByte >> 4)));
        sb.append(byteToHex(secondByte));
        return new DiagnosticTroubleCode(sb.toString());
    }

    /**
    * Check whether the bit in the byte is 0 or 1.
    * @param checkByte is where we want to check the bit
    * @param bit to check
    * @return the boolean show bit is 0 or 1.
    */
    public static boolean checkBit (byte checkByte, int bit) {
        if (bit < 0 || bit > 7) {
            throw new IllegalArgumentException("bit should between 0 to 7");
        }
        return (checkByte >> bit & 1) == 0;
    }

    /**
    * Transfer byte to hex.
    * @param b is byte need to be transferred
    * @return the transferred hex String.
    */
    public static String byteToHex(byte b) {
        return Integer.toHexString(b & 0xF);
    }
}
