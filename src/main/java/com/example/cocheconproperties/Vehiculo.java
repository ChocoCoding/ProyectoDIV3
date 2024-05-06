package com.example.cocheconproperties;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.logging.*;

import java.util.Random;

public class Vehiculo extends ImageView {
    private static final Logger LOGGER = Logger.getLogger(Vehiculo.class.getName());

    private double velocidadX;
    private double velocidadMaxima = 5.0;
    private boolean enMovimiento = false;
    private Random random = new Random();
    private String nombre;
    private AnimationTimer timer;
    private String rutaImagen;

    public Vehiculo(String rutaImagen, String nombre) {
        super(rutaImagen);
        setVelocidadAleatoria();
        this.setFitWidth(150);
        this.setFitHeight(75);
        this.rutaImagen = rutaImagen;
        this.nombre = nombre;
    }


    void setVelocidadAleatoria() {
        velocidadX = Math.random() * velocidadMaxima;
    }

    public void mover() {
        if (!enMovimiento) {
            enMovimiento = true;
            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    double nextX = getLayoutX() + velocidadX;
                    double paneWidth = ((Pane)getParent()).getWidth();
                    // Check if the next X position is within the screen bounds
                    if (nextX + getImage().getWidth() >= paneWidth) {
                        setLayoutX(paneWidth - getImage().getWidth());
                        LOGGER.severe(String.valueOf(nextX));
                        detener();
                    } else {
                        // Adjust X position to ensure the vehicle doesn't go beyond the screen edge
                        setLayoutX(nextX);
                        // Stop the vehicle

                    }
                    // Randomly accelerate the vehicle
                    if (random.nextInt(100) < 1) { // Adjust probability as needed (5% chance here)
                        acelerar();
                    }
                }
            };
            timer.start();
        }
    }

    public void acelerar() {
        velocidadX += random.nextDouble() * 5; // Randomly increase velocity (0 to 2)
        if (velocidadX > velocidadMaxima) {
            velocidadX = velocidadMaxima;
        }
    }

    public void frenar() {
        velocidadX -= 1.0;
        if (velocidadX < 0) {
            velocidadX = 0;
        }
    }


    // Método para detener el vehículo
    public void detener() {
        velocidadX = 0;
        enMovimiento = false;
        if (timer != null) { // Check if timer is initialized before stopping
            timer.stop();
        }
    }

    public double getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(double velocidadX) {
        this.velocidadX = velocidadX;
    }

    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(double velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public boolean isEnMovimiento() {
        return enMovimiento;
    }

    public void setEnMovimiento(boolean enMovimiento) {
        this.enMovimiento = enMovimiento;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AnimationTimer getTimer() {
        return timer;
    }

    public void setTimer(AnimationTimer timer) {
        this.timer = timer;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }


}
