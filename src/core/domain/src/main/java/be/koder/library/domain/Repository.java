package be.koder.library.domain;

import java.util.Optional;

public interface Repository<K, V> {

    Optional<V> get(K id);

    void save(V aggregate);
}