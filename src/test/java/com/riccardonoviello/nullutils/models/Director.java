
package com.riccardonoviello.nullutils.models;

/**
 *
 * @author laptop
 */
public class Director extends Person { // does it really? well, let's say so
    
    private boolean hasFeelings;
    private int firedCounter;
    private int hiredCounter;
    

    /**
     * @return the hasFeelings
     */
    public boolean isHasFeelings() {
        return hasFeelings;
    }

    /**
     * @param hasFeelings the hasFeelings to set
     */
    public void setHasFeelings(boolean hasFeelings) {
        this.hasFeelings = hasFeelings;
    }

    /**
     * @return the firedCounter
     */
    public int getFiredCounter() {
        return firedCounter;
    }

    /**
     * @param firedCounter the firedCounter to set
     */
    public void setFiredCounter(int firedCounter) {
        this.firedCounter = firedCounter;
    }

    /**
     * @return the hiredCounter
     */
    public int getHiredCounter() {
        return hiredCounter;
    }

    /**
     * @param hiredCounter the hiredCounter to set
     */
    public void setHiredCounter(int hiredCounter) {
        this.hiredCounter = hiredCounter;
    }
}
