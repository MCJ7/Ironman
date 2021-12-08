/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcj.ironman.controller;

import com.mcj.ironman.service.Jarvis;
import com.mcj.ironman.enums.PrimaryColors;
import com.mcj.ironman.enums.SecondaryColors;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author maxco
 */
public class Controller {

    private Scanner sc;
    private Jarvis ironman, enemy;

    public Controller() {
        this.sc = new Scanner(System.in);
        ironman = new Jarvis(PrimaryColors.RED, SecondaryColors.ORANGE);
        enemy = new Jarvis(PrimaryColors.BLUE, SecondaryColors.VIOLET);
    }

    public void play() {
        String option= ""; 
        while (!option.equalsIgnoreCase("J") && ironman.getStatus() != 0 && enemy.getStatus() > 0
                && ironman.getBattery() != 0.0 && enemy.getBattery() != 0) {
            System.out.println("-----------------------------------");
            System.out.println(
                    "A.Walk"
                    + "\nB.Run"
                    + "\nC.Propel"
                    + "\nD.Flight"
                    + "\nE.Audio"
                    + "\nF.Texting"
                    + "\nG.View status"
                    + "\nH.Atack"
                    + "\nI.Regenerate"
                    + "\nJ.Abandon battle");
            System.out.println("-----------------------------------");
            option = sc.next();

            switch (option) {
                case "A" ->
                    walk();
                case "B" ->
                    run();
                case "C" ->
                   propel();
                case "D" ->
                    flight();
                case "E" ->
                    talk();
                case "F" ->
                    write();
                case "G" ->
                    viewStatus();
                case "H" ->
                    atacarEnemigo();
                case "I" -> {
                    ironman.regenerate();
                    viewStatus();
                }
                case "J" ->
                    System.out.println("Bye bye");
                default ->
                    System.out.println("Option not available");
            }

        }
        if (enemy.getStatus() == 0) {
            result(1);
        } else if (enemy.getBattery() == 0) {
            result(2);
        } else if (ironman.getStatus() == 0) {
            result(3);
        } else {
            result(4);
        }
    }

    public void result(int option) {
        switch (option) {
            case 1 ->
                System.out.println("Your enemy doesn´t resist the damage, Won!");
            case 2 ->
                System.out.println("The battery's enemy ran out, Won!");
            case 3 ->
                System.out.println("Your overall state is 0%, Lose!");
            case 4 ->
                System.out.println("Your battery ran out, Lose!");
        }

    }

    public void viewStatus() {
        System.out.println("My status");
        System.out.println(ironman.status());
        System.out.println("");
        System.out.println("Enemy's status");
        System.out.println(enemy.status());
        System.out.println("");
    }

    public void walk() {
        System.out.print("How many steps: ");
        int action = sc.nextInt();
        System.out.print("Intensity:");
        int intensity = sc.nextInt();
        double aux = ironman.walk(action, intensity);
        batteryIndicator(aux);
        action = (int) (Math.random() * 15);
        intensity = (int) (Math.random() * 3);
        enemy.run(action, intensity);

    }

    public void batteryIndicator(double aux) {
        if (aux > 0) {
            System.out.println("Spend required: " + aux + " %");
        } else {
            System.out.println("The state battery does not resist the action");
        }
    }

    public void run() {
        System.out.print("How many steps: ");
        int action = sc.nextInt();
        System.out.print("Intensity:");
        int intensity = sc.nextInt();
        double aux = ironman.run(action, intensity);
        batteryIndicator(aux);

        action = (int) (Math.random() * 15);
        intensity = (int) (Math.random() * 3);
        enemy.walk(action, intensity);

    }

    public void flight() {
        System.out.print("How many seconds would do you like flight: ");
        int action = sc.nextInt();
        System.out.print("Intensity:");
        int intensity = sc.nextInt();

        double aux = ironman.flight(action, intensity);
        batteryIndicator(aux);

        action = (int) (Math.random() * 15);
        intensity = (int) (Math.random() * 3);
        enemy.flight(action, intensity);

    }

    public void propel() {
        System.out.print("How many seconds would do you like propel: ");
        int action = sc.nextInt();
        System.out.print("Intensidad:");
        int intensity = sc.nextInt();

        double aux = ironman.propel(action, intensity);
        batteryIndicator(aux);
        action = (int) (Math.random() * 15);
        intensity = (int) (Math.random() * 3);
        enemy.propel(action, intensity);

    }

    public void talk() {
        System.out.print("How many seconds would do you like talk: ");
        int action = sc.nextInt();
        double aux = ironman.talk(action);
        batteryIndicator(aux);
        action = (int) (Math.random() * 15);
        enemy.talk(action);

    }

    public void write() {
        System.out.print("Letters : ");
        int action = sc.nextInt();

        double aux = ironman.write(action);
        batteryIndicator(aux);
        action = (int) (Math.random() * 15);
        enemy.write(action);

    }

    public void enemyAttack() {

        ironman.pointDamage(((int) (Math.random() * 6)),( (int) (Math.random() * 9)), ((int) (Math.random() * 9)));    

    }

    public void atacarEnemigo() {
        String eleccion;
        int place = 10;
        do {
            enemyAttack();
            System.out.println("Damage");
            int attack = sc.nextInt();
            System.out.print("Intensity:");
            int intensity = sc.nextInt();
            System.out.print("Choose place to attack:");
            System.out.println(
                    "1.Head"
                    + "\n2.Chest"
                    + "\n3.Right glove"
                    + "\n4.Link glove"
                    + "\n5.Right boot"
                    + "\n6.Link boot");
            String optionString = sc.next();
            try {
                place=(Integer.parseInt(optionString)-1);
            } catch (NumberFormatException exception) {
                System.out.println("No escribiste un número " +exception);
            }
            if (place>=0&&place<7) {
                enemy.pointDamage(place, attack, intensity);
                
            }else System.out.println("Place not available");
            
            System.out.println("Would you like realize another attack <Y><N>");
            eleccion = sc.next();
        } while (eleccion.equalsIgnoreCase("Y"));
        viewStatus();

    }

    public String menu() {
        ironman.reboot();
        enemy.reboot();
        System.out.println("---------------------------------------");
        System.out.println(
                "A.Play "
                + "\nB.Exit");
        System.out.println("Choose a option");
        String opcion = sc.next();
        
        return opcion.toUpperCase();

    }
}
