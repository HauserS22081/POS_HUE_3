package net.htlgrieskirchen.pos3.streams;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<String, T> {
    T map(String input);

    default List<T> mapAll(List<String> input) {
        return input.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
