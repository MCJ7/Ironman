
package com.mcj.ironman.enums;


/**
 *
 * @author maxco
 */
public enum Spending {
    
    
    BOOT(0.04),GLOVE(0.05),SYNTHESIZER(0.02),WRITING(0.002);
    
    private double value; 
    public double getSpending(){
        return value;
    } 

    private Spending(double value) {
        this.value = value;
    }
    
}
