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

    public void changePower (int[] propellersTargetPower) {


    }

    public int[] getPropellersCurrentPower (List<Propeller> propellers) {
        int i = 0;
        int [] propellersCurrentPower = new int[propellers.size()];
        for (Propeller p : propellers) {
            propellersCurrentPower[i] = p.getCurrentPower();
        }
        return propellersCurrentPower;
    }

}
