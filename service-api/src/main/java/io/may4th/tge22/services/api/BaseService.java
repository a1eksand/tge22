package io.may4th.tge22.services.api;

import java.util.UUID;

interface BaseService<T, N> {

    T findById(UUID id);

    T save(N newTO);
}
