package comparator;

import sweet.Sweetness;

public class SweetnessSortByWeight implements SweetnessSort {
    @Override
    public int compare(Sweetness o1, Sweetness o2) {
        return Double.compare(o1.getWeight(), o2.getWeight());
    }
}
