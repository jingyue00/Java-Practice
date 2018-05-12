package edu.nyu.cs9053.homework9;

/**
 * User: blangel
 */
public interface Customer {

    /**
     * Attempts to order by checking {@code queue} and if there's an available position places an order resulting in
     * an {@link OrderNumber}.
     *
     * @param queue to place the order and get a new {@link OrderNumber} if possible
     * @return the produced {@link OrderNumber} or null if the {@link Customer} must continue to wait before ordering
     */
    OrderNumber order(Queue queue);

}
