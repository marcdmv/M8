package itacademy;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int[] rocket1PropellersPower = new int[]{10,30,80};
        int[] rocket2PropellersPower = new int[]{30,40,50,50,30,10};
        List<Propeller> propellers = new ArrayList<>();

        Rocket rocket1 = new Rocket();
        Rocket rocket2 = new Rocket();

        rocket1.setCode("32WESSDS");
        rocket2.setCode("LDSFJA32");

        for (int power : rocket1PropellersPower){
            Propeller propeller = new Propeller();

            propeller.setPower(power);

            propellers.add(propeller);

        }

        for (Propeller p : propellers)
        {
            System.out.println(p.getPower());
        }


    }
}
