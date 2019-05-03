package repository;



import comparator.SweetnessSort;
import sweet.Sweetness;

import java.util.ArrayList;

public class Gift {
    public ArrayList<Sweetness> sweets = new ArrayList();



    public void add(Sweetness sweet) {

        sweets.add(sweet);
    }
public void delete(int index){
    sweets.remove(index);
}


    public double getWeight() {
        double weight = 0;
        for (Sweetness sweet : sweets) {
            weight += sweet.getWeight();
        }
        return weight;
    }

    public Sweetness range(double minSugar, double maxSugar) {
        for (Sweetness sweet : sweets
                ) {
            if (sweet.getSugar() >= minSugar && sweet.getSugar() <= maxSugar)
                return sweet;
        }
        return null;
    }

    public void sort(SweetnessSort t) {

        sweets.sort(t);
    }

}
