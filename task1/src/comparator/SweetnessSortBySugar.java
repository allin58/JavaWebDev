package comparator;

import sweet.Sweetness;

public class SweetnessSortBySugar implements SweetnessSort {
    @Override
    public int compare(Sweetness o1, Sweetness o2) {
        return Double.compare(o1.getSugar(), o2.getSugar());
    }
}
