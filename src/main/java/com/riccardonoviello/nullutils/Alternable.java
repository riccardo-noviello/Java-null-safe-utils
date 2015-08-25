
package com.riccardonoviello.nullutils;

/**
 *
 * @author laptop
 */
public class Alternable {
    
    private Object object;

    public Alternable(Object o){
        this.object = o;
    }
    
    public <T>T or(Object alternative){
        return (T) ((object!=null)?object : alternative);
    }
    
    /**
     * @return the object
     */
    public Object getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(Object object) {
        this.object = object;
    }

    
}
