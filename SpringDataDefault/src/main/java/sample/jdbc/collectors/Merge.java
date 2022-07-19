package sample.jdbc.collectors;

import java.util.Collection;
import java.util.function.Function;

public class Merge<PARENT, CHILD> {

    Function<PARENT, Collection<CHILD>> listGetter;
    PARENT firstParent = null;

    public Merge(Function<PARENT, Collection<CHILD>> listGetter) {
        this.listGetter = listGetter;
    }

    public void accept(PARENT parent) {
        if (firstParent != null) {
            listGetter.apply(firstParent).addAll(listGetter.apply(parent));
        } else {
            firstParent = parent;
        }
    }
}
