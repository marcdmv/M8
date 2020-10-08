package itacademy;

public class Propeller {

    private int maxPower;
    private int currentPower;

    public Propeller(){
        currentPower = 0;
    }

    public void setMaxPower(int maxPower){

        this.maxPower = maxPower;
    }

    public int getMaxPower(){

        return maxPower;
    }

    public void setCurrentPower(int currentPower) throws Exception {
        if (currentPower <= maxPower && currentPower >= 0) {
            this.currentPower = currentPower;
        } else if (currentPower < 0){
            throw new Exception("La potencia objetivo no puede ser negativa: " + currentPower);
        }
        else {
            throw new Exception("La potencia objetivo " + currentPower + " es superior al máximo del propulsor, que es " + maxPower);
        }
    }

    public int getCurrentPower(){

        return currentPower;
    }

}
