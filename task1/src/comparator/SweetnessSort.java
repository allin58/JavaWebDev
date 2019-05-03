package comparator;

import sweet.Sweetness;

import java.util.Comparator;

public interface SweetnessSort extends Comparator<Sweetness> {
    @Override
    int compare(Sweetness o1, Sweetness o2);
}
