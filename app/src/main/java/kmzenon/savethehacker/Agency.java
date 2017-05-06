package kmzenon.savethehacker;

/**
 * Created by Naray on 06-05-2017.
 */
public class Agency {

    String name, total_prod, remaining_prod;
    float msp;

    public Agency(){

    }

    public Agency(String name, float msp, String total_prod, String remaining_prod){
        this.name = name;
        this.msp = msp;
        this.total_prod = total_prod;
        this.remaining_prod = remaining_prod;
    }

    public String getName() {
        return name;
    }

    public String getTotal_prod() {
        return total_prod;
    }

    public String getRemaining_prod() {
        return remaining_prod;
    }

    public float getMsp() {
        return msp;
    }
}
