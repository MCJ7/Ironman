
package com.mcj.ironman;

import com.mcj.ironman.controller.Controller;

/**
 *
 * @author maxco
 */
public class IronmanApplication {

    public static void main(String[] args) {
        Controller servicio = new Controller();
        String opcion;

        do {
            opcion = servicio.menu();
            switch (opcion) {
                case "A" ->
                    servicio.play();
                case "B" ->
                    System.out.println("Nos vemos");
                default ->
                    System.err.println("La opción no está disponible");
            }
        } while (!opcion.equalsIgnoreCase("B"));
    }

}
