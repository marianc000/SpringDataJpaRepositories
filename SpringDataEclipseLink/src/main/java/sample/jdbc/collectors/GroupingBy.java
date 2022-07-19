package sample.jdbc.collectors;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;

public class GroupingBy {

    public static <PARENT, ID, CHILD> Collector<PARENT, ?, List<PARENT>> groupingBy(Function< PARENT, ID> classifier,
            Function<PARENT, Collection<CHILD>> listGetter) {

        BiConsumer<Map<ID, Merge<PARENT, CHILD>>, PARENT> accumulator = (map, parent) -> {
            ID id = classifier.apply(parent);
            map.computeIfAbsent(id, k -> new Merge<>(listGetter)).accept(parent);
        };

        Function<Map<ID, Merge<PARENT, CHILD>>, List<PARENT>> finisher = map
                -> map.values().stream().map(v -> v.firstParent).toList();

        BinaryOperator<Map<ID, Merge<PARENT, CHILD>>> combiner = (m1, m2) -> {
            throw new UnsupportedOperationException("Not needed");
        };
 
       return Collector.of(LinkedHashMap::new,  accumulator, combiner, finisher );
    }
}
