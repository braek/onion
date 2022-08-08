package be.koder.library.domain;

import java.util.Optional;

public interface Repository<K, V> {

    Optional<V> getById(K id);

    void save(V aggregate);
}