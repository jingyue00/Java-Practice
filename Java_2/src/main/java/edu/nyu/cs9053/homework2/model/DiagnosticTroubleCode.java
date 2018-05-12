package edu.nyu.cs9053.homework2.model;

/**
 * User: blangel
 */
public class DiagnosticTroubleCode {

    public static DiagnosticTroubleCode construct(String code) {
        return new DiagnosticTroubleCode(code);
    }

    private final String code;

    public DiagnosticTroubleCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
