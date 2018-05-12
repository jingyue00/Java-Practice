package edu.nyu.cs9053.homework2.model;

/**
 * User: blangel
 */
public class EngineLightAlert {

    public static EngineLightAlert construct(String vehicleId, long dateTime, DiagnosticTroubleCode ... codes) {
        return new EngineLightAlert(vehicleId, dateTime, codes);
    }

    private final String vehicleId;

    private final long dateTime;

    private final DiagnosticTroubleCode[] codes;

    public EngineLightAlert(String vehicleId, long dateTime, DiagnosticTroubleCode[] codes) {
        this.vehicleId = vehicleId;
        this.dateTime = dateTime;
        this.codes = codes;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public long getDateTime() {
        return dateTime;
    }

    public DiagnosticTroubleCode[] getCodes() {
        return codes;
    }
}
