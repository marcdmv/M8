package itacademy;

import java.util.ArrayList;
import java.util.List;

public class Rocket {

    private String code;

    public Rocket (){

    }

    public void setCode (String code){

        this.code = code;
    }
    public String getCode (){

        return code;
    }

    public void changePower (double[] propellersTargetPower) {


    }

    public double[] getPropellersCurrentPower (List<Propeller> propellers) {
        int i = 0;
        double [] propellersCurrentPower = new double[propellers.size()];
        for (Propeller p : propellers) {
            propellersCurrentPower[i] = p.getCurrentPower();
        }
        return propellersCurrentPower;
    }

    public double changeSpeed (double v0, double v1) {
        try {
            double totalPower;
            totalPower = Math.pow(((v1 - v0) / 100), 2);

            return totalPower;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
}
