/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcj.ironman.service;

/**
 *
 * @author maxco
 */
public class Part {
    private String part;
    private double value;

    public Part(String parte) {
        this.part = parte;
        this.value = 100;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    
    
}
