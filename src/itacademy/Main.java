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



        int[] rocket1PropellersTargetPower = new int[]{5,20,60};
        i = 0;
        int[] rocketPropellersCurrentPower = rocket1.getPropellersCurrentPower(propellers1);
        for (Propeller p : propellers1) {
            try {
                p.setCurrentPower(rocket1PropellersTargetPower[i]);
            }
            catch (Exception e){
                System.out.println(e);
                System.exit(1);
            }
            i++;
        }

        i = 0;
        System.out.println("El cohete " + rocket1.getCode() + " ha ajustado la potencia de los propulsores a los valores siguientes:");
        for (Propeller p : propellers1) {
            System.out.println(i+1 + ": " + rocketPropellersCurrentPower[i] + " -> " + p.getCurrentPower());
            i++;
        }


    }
}

class PropellerThreads implements java.lang.Runnable {

    public PropellerThreads(Propeller propeller, int targetPower){
        this.propeller = propeller;
        this.targetPower = targetPower;
    }

    public void run(){


    }

    private Propeller propeller;
    private int targetPower;
}
