package itacademy;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int[] rocket1PropellersPower = new int[]{10,30,80};
        int[] rocket2PropellersPower = new int[]{30,40,50,50,30,10};
        List<Propeller> propellers1 = new ArrayList<>();
        List<Propeller> propellers2 = new ArrayList<>();

        Rocket rocket1 = new Rocket();
        Rocket rocket2 = new Rocket();

        rocket1.setCode("32WESSDS");
        rocket2.setCode("LDSFJA32");

        for (int power : rocket1PropellersPower){
            Propeller propeller = new Propeller();
            propeller.setMaxPower(power);
            propellers1.add(propeller);
        }

        for (int power : rocket2PropellersPower){
            Propeller propeller = new Propeller();
            propeller.setMaxPower(power);
            propellers2.add(propeller);
        }

        System.out.println("FASE 1");
        System.out.println("El cohete " + rocket1.getCode() + " tiene " + propellers1.size() + " propulsores.");
        System.out.println("El cohete " + rocket2.getCode() + " tiene " + propellers2.size() + " propulsores.");

        System.out.println("FASE 2");
        int i = 0;
        System.out.println("El cohete " + rocket1.getCode() + " tiene unos propulsores con las potencias máximas siguientes:");
        for (Propeller p : propellers1) {
            System.out.println(i+1 + ": " + p.getMaxPower());
            i++;
        }
        i = 0;
        System.out.println("El cohete " + rocket2.getCode() + " tiene unos propulsores con las potencias máximas siguientes:");
        for (Propeller p : propellers2) {
            System.out.println(i+1 + ": " + p.getMaxPower());
            i++;
        }
        
        int[] rocket1PropellersTargetPower = new int[]{5,25,75};
        int[] rocket2PropellersTargetPower = new int[]{25,35,45,45,20,5};
        java.lang.Runnable r1 = new RocketThreads(rocket1, propellers1, rocket1PropellersTargetPower);
        java.lang.Runnable r2 = new RocketThreads(rocket2, propellers2, rocket2PropellersTargetPower);
        new Thread(r1).start();
        new Thread(r2).start();
    }
}

class RocketThreads implements java.lang.Runnable {

    private Rocket rocket;
    private List<Propeller> propellers;
    private int i = 0;
    private int[] propellersTargetPower;

    public RocketThreads(Rocket rocket, List<Propeller> propellers, int[] propellersTargetPower){
        this.rocket = rocket;
        this.propellers = propellers;
        this.propellersTargetPower = propellersTargetPower;
    }

    public void run(){

        int[] rocketPropellersCurrentPower = rocket.getPropellersCurrentPower(propellers);
        for (Propeller p : propellers) {
            try {
                p.setCurrentPower(propellersTargetPower[i]);
            }
            catch (Exception e){
                System.out.println(e);
                System.exit(1);
            }
            i++;
        }

        i = 0;
        System.out.println("El cohete " + rocket.getCode() + " ha ajustado la potencia de los propulsores a los valores siguientes:");
        for (Propeller p : propellers) {
            int propellerNumber = i+1;
            System.out.println("Rocket " + rocket.getCode() + " - Propeller #" + propellerNumber + ": " + rocketPropellersCurrentPower[i] + " -> " + p.getCurrentPower());
            i++;
        }

        double totalPower;
        double newPower;
        double v0 = 0;
        double v1 = 200;
        totalPower = rocket.changeSpeed(v0, v1);
        System.out.println("La potencia total necesaria para el cambio de velocidad es de: " + totalPower + " unidades de energía");
        i = 0;
        for (Propeller p : propellers) {
            if (totalPower <= 0) {
                newPower = p.getCurrentPower();
            }
            else if (p.getMaxPower()-p.getCurrentPower() >= totalPower) {
                newPower = p.getCurrentPower()+totalPower;
            }
            else {
                newPower = p.getMaxPower();
            }
            int propellerNumber = i+1;
            System.out.println("Rocket " + rocket.getCode() + " - Propeller #" + propellerNumber + ": " + p.getCurrentPower() + " -> " + newPower);
            totalPower -= p.getMaxPower()-p.getCurrentPower();
            i++;
        }
        if (totalPower > 0) {
            System.out.println("ERROR: Faltan por asignar " + totalPower + " unidades de energía al cohete " + rocket.getCode() + " para pasar de la velocidad " + v0 + " a la velocidad " + v1);
        }
        else {
            System.out.println("Potencia de los propulsores modificada correctamente");
        }

    }
}
