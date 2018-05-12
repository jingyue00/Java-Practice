package edu.nyu.cs9053.homework2;

import edu.nyu.cs9053.homework2.model.EngineLightAlert;
import edu.nyu.cs9053.homework2.model.DiagnosticTroubleCode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: blangel
 *
 * @see {@literal https://www.json.org/}
 * @see {@literal https://en.wikipedia.org/wiki/JSON}
 */
public class JsonParser {

    /**
     * @param alert to serialize into {@literal JSON}
     * @implNote a null value should be an {@linkplain IllegalArgumentException}; i.e. {@code throw new IllegalArgumentException}
     * @return the serialized {@literal JSON} representation of {@code alert}
     */
    public static String toJson(EngineLightAlert alert) {
    	if (alert == null) {
    		throw new IllegalArgumentException("alert cannot be null!");
    	}
    	StringBuffer sb = new StringBuffer();
    	sb.append("{");

    	// If alert has Vehicle ID, add ID to string buffer.
    	if (alert.getVehicleId() != null) {
    		sb.append("\"vehicleId\":");
    		sb.append("\"" + escapeQuotes(alert.getVehicleId()) + "\",");
    	}

    	// Add Date Time to string buffer.
        sb.append("\"dateTime\":");
        sb.append(alert.getDateTime() + ",");
    	
    	// If the Codes of alert are not null and their length are larger than 0, add them to string buffer.
    	if (alert.getCodes() != null && alert.getCodes().length != 0) {
    		sb.append("[");
    		for (DiagnosticTroubleCode dTCode : alert.getCodes()){
    			if (dTCode != null) {
	    			if (dTCode.getCode() == null) {
	    				sb.append("{},");
	    			} else {
	    				sb.append("{\"code\":");
	    				sb.append("\"" + dTCode.getCode() + "\"},");
    				}
    			}
    		}
    		sb.deleteCharAt(sb.length()-1);
    		sb.append("],");
    	}   	
    	sb.deleteCharAt(sb.length()-1);
    	sb.append("}");
        return sb.toString();
    }

    /**
    * escape quotes If the string of Vehicle ID has " replace it to \"
    * @param input is the string to parse 
    * @return the replaced string 
    */
    public static String escapeQuotes(String input) {
        return input.replaceAll(Pattern.quote("\""), Matcher.quoteReplacement("\\\""));
    }
}
