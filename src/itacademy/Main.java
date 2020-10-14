package itacademy;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Creamos los dos objetos de tipo Rocket
        Rocket rocket1 = new Rocket();
        Rocket rocket2 = new Rocket();

        // Creamos una lista de propulsores por cohete
        List<Propeller> rocket1Propellers;
        List<Propeller> rocket2Propellers;

        // Definimos manualmente las poténcias máximas de cada propulsor de ambos cohetes
        double[] rocket1PropellersPower = new double[]{10,30,80};
        double[] rocket2PropellersPower = new double[]{30,40,50,50,30,10};

        // Utilizamos el método setCode para asignar un nombre a cada cohete
        rocket1.setCode("32WESSDS");
        rocket2.setCode("LDSFJA32");

        // Asignamos la potencia máxima a cada propulsor utilizando el método setMaxPowerPropellers
        rocket1Propellers = setMaxPowerPropellers(rocket1PropellersPower);
        rocket2Propellers = setMaxPowerPropellers(rocket2PropellersPower);

        // FASE 1
        // Mostramos por consola cuántos propulsores tiene cada cohete
        System.out.println("El cohete " + rocket1.getCode() + " tiene " + rocket1Propellers.size() + " propulsores.");
        System.out.println("El cohete " + rocket2.getCode() + " tiene " + rocket2Propellers.size() + " propulsores.");

        // FASE 2
        // Mostramos por consola la potencia máxima de cada propulsor usando el método getter getMaxPower() para cada propulsor
        showPropellersMaxPower (rocket1, rocket1Propellers);
        showPropellersMaxPower (rocket2, rocket2Propellers);

        // FASE 3 y 4
        // Definimos manualmente la potencia objetivo de cada propulsor.
        double[] rocket1PropellersTargetPower = new double[]{5,25,75};
        double[] rocket2PropellersTargetPower = new double[]{25,35,45,45,20,5};
        // Definimos las velocidades inicial y final de los cohetes. Para simplificar, se asignan para ambos cohetes, pero se podrían asignar velocidades distintas para cada cohete.
        // v1 = v0 + 100 * sqrt(PotenciaTotal)
        // PotenciaTotal = ((v1 - v0)/100)^2
        double v0 = 150;
        double v1 = 500; // Es importante remarcar que la fórmula define la PotentiaTotal necesaria siempre positiva, aunque el cohete frene. Por este motivo no se permite una v0 > v1
        // Montamos y ejecutamos los hilos. Uno para cada cohete.
        java.lang.Runnable r1 = new RocketThreads(rocket1, rocket1Propellers, rocket1PropellersTargetPower, v0, v1);
        java.lang.Runnable r2 = new RocketThreads(rocket2, rocket2Propellers, rocket2PropellersTargetPower, v0, v1);
        new Thread(r1).start();
        new Thread(r2).start();
    }

    public static void showPropellersMaxPower (Rocket rocket, List<Propeller> propellers) {
        int i = 1;
        System.out.println("Los propulsores del cohete " + rocket.getCode() + " tienen las potencias máximas siguientes:");
        for (Propeller p : propellers) {
            System.out.println("Rocket " + rocket.getCode() + " - Propeller #" + i + ": " + p.getMaxPower());
            i++;
        }
    }

    public static List<Propeller> setMaxPowerPropellers (double[] rocketPropellersPower) {
        List<Propeller> rocketPropellers = new ArrayList<>();
        for (double power : rocketPropellersPower){
            Propeller propeller = new Propeller();
            propeller.setMaxPower(power);
            rocketPropellers.add(propeller);
        }
        return rocketPropellers;
    }
}

class RocketThreads implements java.lang.Runnable {

    private final Rocket rocket;
    private final List<Propeller> propellers;
    private final double[] propellersTargetPower;
    private double v0;
    private double v1;

    public RocketThreads(Rocket rocket, List<Propeller> propellers, double[] propellersTargetPower, double v0, double v1){
        this.rocket = rocket;
        this.propellers = propellers;
        this.propellersTargetPower = propellersTargetPower;
        this.v0 = v0;
        this.v1 = v1;
    }

    public void run(){

        double[] rocketPropellersCurrentPower = rocket.getPropellersCurrentPower(propellers);

        int i = 0;
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

        i = 1;
        System.out.println("El cohete " + rocket.getCode() + " ha ajustado la potencia de los propulsores a los valores siguientes:");
        for (Propeller p : propellers) {
            System.out.println("Rocket " + rocket.getCode() + " - Propeller #" + i + ": " + rocketPropellersCurrentPower[i-1] + " -> " + p.getCurrentPower());
            i++;
        }

        double requiredTotalPower;

        if (v0 > v1) {
            System.out.println("La velocidad final debe ser superior a la inicial");
            System.exit(1);
        }

        requiredTotalPower = rocket.changeSpeed(v0, v1);
        System.out.println("La potencia total necesaria para el cambio de velocidad es de: " + requiredTotalPower + " unidades de energía.");

        double remainingRequiredPower = requiredTotalPower;
        powerDistribution(remainingRequiredPower, propellers);

    }

    public void powerDistribution(double remainingRequiredPower, List<Propeller> propellers) {
        int i = 1;
        double newPower;
        for (Propeller p : propellers) {
            if (remainingRequiredPower <= 0) {
                newPower = p.getCurrentPower();
            }
            else if (p.getMaxPower()-p.getCurrentPower() >= remainingRequiredPower) {
                newPower = p.getCurrentPower()+remainingRequiredPower;
            }
            else {
                newPower = p.getMaxPower();
            }
            System.out.println("Rocket " + rocket.getCode() + " - Propeller #" + i + ": " + p.getCurrentPower() + " -> " + newPower);
            remainingRequiredPower -= p.getMaxPower()-p.getCurrentPower();
            i++;
        }
        if (remainingRequiredPower > 0) {
            System.out.println("ERROR: Faltan por asignar " + remainingRequiredPower + " unidades de energía al cohete " + rocket.getCode() + " para pasar de la velocidad " + v0 + " a la velocidad " + v1);
        }
        else {
            System.out.println("SUCCESS: La potencia de los propulsores del cohete " + rocket.getCode() + " ha sido modificada correctamente.");
        }
    }
}
