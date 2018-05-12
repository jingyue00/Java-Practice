package edu.nyu.cs9053.homework9;

/**
 * User: blangel
 */
public interface Barista {

    /**
     * Processes an {@linkplain OrderNumber} within {@link Queue} from {@code from} only if there's orders within it.
     *
     * @param from to extract a {@link OrderNumber} if any
     * @return the extracted {@link OrderNumber} or null if none available to be extracted
     */
    OrderNumber handle(Queue from);

}
