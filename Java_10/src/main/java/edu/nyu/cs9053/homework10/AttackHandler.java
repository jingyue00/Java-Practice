package edu.nyu.cs9053.homework10;

/**
 * User: blangel
 */
public interface AttackHandler {

    /**
     * Invoked when the attack can be fought off
     * Note, this should only be invoked if the calling thread is actually able to ward off the attack.
     */
    void soldiersReady();

}
