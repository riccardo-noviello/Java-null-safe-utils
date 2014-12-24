package com.riccardonoviello.nullutils.models;

/**
 *
 * @author laptop
 */
public class Employee extends Person {
    
    private String natInsNo;
    
    /**
     * @return the natInsNo
     */
    public String getNatInsNo() {
        return natInsNo;
    }

    /**
     * @param natInsNo the natInsNo to set
     */
    public void setNatInsNo(String natInsNo) {
        this.natInsNo = natInsNo;
    }
    
}
