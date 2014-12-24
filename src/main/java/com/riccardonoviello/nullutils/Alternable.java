
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
    
    public Object or(Object alternative){
        return (object!=null)?object : alternative;
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
