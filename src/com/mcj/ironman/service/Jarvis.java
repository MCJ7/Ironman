/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcj.ironman.service;

import com.mcj.ironman.enums.PrimaryColors;
import com.mcj.ironman.enums.SecondaryColors;
import com.mcj.ironman.enums.Spending;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maxco
 */
public class Jarvis {

    private List<Part> statusParts;
    private PrimaryColors primary;
    private SecondaryColors secondary;

    public Jarvis(PrimaryColors primary, SecondaryColors secondary) {
        this.statusParts = new ArrayList();
        this.primary = primary;
        this.secondary = secondary;
        autocomplete();
    }
    private void autocomplete(){//Completa el status de salud
        
        statusParts.add(0,new Part("Head"));
        statusParts.add(1,new Part("Chest"));
        statusParts.add(2,new Part("Right Glove"));
        statusParts.add(3,new Part("Link Glove"));
        statusParts.add(4,new Part("Right boot"));
        statusParts.add(5,new Part("Link boot"));
        statusParts.add(6,new Part("State"));
        statusParts.add(7,new Part("battery"));  
    }
    public void reboot() {
        statusParts.forEach(sector -> {
            sector.setValue(100);
        });
    }
    private double action(int place,int secondPlace,int medida, int intensity,double myConst){
        //Se puede utilizar si el danio es mayor al 70
        if (statusParts.get(place).getValue() > 70 || statusParts.get(secondPlace).getValue() > 70) {
            double aux = (medida * myConst) * intensity;
            return auxiliarBateria(aux);
        }
        return 0;
    }
    public double walk(int steps, int intensity) {
        System.out.println(Spending.BOOT.getSpending());
        return action(4, 5,steps, intensity,Spending.BOOT.getSpending());
        
    }

    public double run(int steps, int intensity) {
        return action(4, 5,steps, intensity,Spending.BOOT.getSpending()*2);
    }

    public double propel(int minutes, int intensity) {
        return action(4, 5,minutes, intensity,Spending.BOOT.getSpending()*3);
    }

    public double flight(int minutes, int intensity) {
        return action(4, 5,minutes, intensity,Spending.BOOT.getSpending()*3)
                +action(4, 5,minutes, intensity,Spending.GLOVE.getSpending()*2);
    }

    public double shot(int minutos, int intensity) {
        return action(2, 3,minutos, intensity,Spending.GLOVE.getSpending()*3);
    }

    private double auxiliarBateria(double aux) {
        if (statusParts.get(7).getValue() - aux > 0) {
            statusParts.get(7).setValue(statusParts.get(0).getValue()-aux);
        } else {
            aux = statusParts.get(7).getValue();
            statusParts.get(7).setValue(0);
        }
        return aux;
    }

    public double talk(int seconds) {
        return action(0, 0,seconds, 1,Spending.SYNTHESIZER.getSpending());
    }

    public double write(int text) {
        return action(0, 0,text, 1,Spending.WRITING.getSpending());
    }

    public String status() {
        return "State:" + "\nBattery: " + statusParts.get(7).getValue() + "%\nHead: " + statusParts.get(0).getValue()
                + "%\nBody: " + statusParts.get(1).getValue()
                + "%\nRight glove: " +statusParts.get(2).getValue() + "%\nLink glove: " + statusParts.get(3).getValue()
                + "%\nRight boot: " + statusParts.get(4).getValue() + "%\nLink boot: "
                + statusParts.get(5).getValue() + "%\nOverallStatus: " + statusParts.get(6).getValue() + "%";
    }
    
    public void pointDamage(int point,int atacks, int intensity){
        
        if (statusParts.get(point).getValue() > 0) {
            if ((statusParts.get(point).getValue() - (atacks * intensity)) > 0) {
                statusParts.get(point).setValue(statusParts.get(point).getValue() - (atacks * intensity));
            } else {
                statusParts.get(point).setValue(0);
            }
            
        }else statusParts.get(point).setValue(0);
        updateStatus();
       
    }

    public double getStatus() {
        return statusParts.get(6).getValue();
    }

    public void updateStatus() {
        double aux=0;
        for (int i = 0; i < 6; i++) {
            aux += statusParts.get(i).getValue();
        }
        statusParts.get(6).setValue((double) Math.round(((aux) / 6) * 100) / 100);
        if (statusParts.get(6).getValue()<70) {
            regenerate();
        }
    }

    public double getBattery() {
        return statusParts.get(7).getValue();
    }

    public void regenerate() {
        double aux = (double) (1 + ((Math.random() * 40) / 100));
        System.out.println(aux);
        for (int i = 0; i < 6; i++) {
            if (statusParts.get(i).getValue()*aux<100) {
                statusParts.get(i).setValue(statusParts.get(i).getValue()*aux);
            }else statusParts.get(i).setValue(100);
            aux += statusParts.get(i).getValue();
        }
        updateStatus();
    }

    public List<Part> getStatusParts() {
        return statusParts;
    }
    public int distance(){

        int intensity = (int)(Math.random()*8);
        if (intensity<=5) {
            return intensity;
        }
        return 0;
    }
}
