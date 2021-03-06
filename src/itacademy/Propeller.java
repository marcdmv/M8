package itacademy;

public class Propeller {

    private double maxPower;
    private double currentPower;

    public Propeller(){
        currentPower = 0;
    }

    public void setMaxPower(double maxPower){

        this.maxPower = maxPower;
    }

    public double getMaxPower(){

        return maxPower;
    }

    public void setCurrentPower(double currentPower) throws Exception {
        if (currentPower <= maxPower && currentPower >= 0) {
            this.currentPower = currentPower;
        } else if (currentPower < 0){
            throw new Exception("La potencia objetivo no puede ser negativa: " + currentPower);
        }
        else {
            throw new Exception("La potencia objetivo " + currentPower + " es superior al máximo del propulsor, que es " + maxPower);
        }
    }

    public double getCurrentPower(){

        return currentPower;
    }

}
