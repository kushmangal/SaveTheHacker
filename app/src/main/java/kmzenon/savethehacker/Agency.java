package kmzenon.savethehacker;

/**
 * Created by Naray on 06-05-2017.
 */
public class Agency {

    String name;
    int msp, total_prod, remaining_prod, id;

    public Agency(){

    }

    public Agency(String name, int msp, int total_prod, int remaining_prod,int id){
        this.name = name;
        this.msp = msp;
        this.total_prod = total_prod;
        this.remaining_prod = remaining_prod;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public int getTotal_prod() {
        return total_prod;
    }

    public int getRemaining_prod() {
        return remaining_prod;
    }

    public int getMsp() {
        return msp;
    }
    public int getid() {
        return id;
    }
}
