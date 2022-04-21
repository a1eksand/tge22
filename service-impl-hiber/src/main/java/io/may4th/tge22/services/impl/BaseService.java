package io.may4th.tge22.services.impl;

import io.may4th.tge22.services.api.exceptions.ResourceNotFoundException;
import io.may4th.tge22.services.impl.mappers.BaseMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
abstract class BaseService<E, T, N> {

    private final BaseMapper<E, T, N> mapper;
    private final PagingAndSortingRepository<E, UUID> repository;

    @Transactional(readOnly = true)
    public T findById(UUID id) {
        return mapper.to(repository.findById(id).orElseThrow(() -> notFound(id)));
    }

    RuntimeException notFound(UUID id) {
        return new ResourceNotFoundException(repository.getClass().getSimpleName(), id.toString());
    }

    T saveNew(N newTO) {
        return saveOrUpdate(mapper.en(newTO));
    }

    T saveOrUpdate(E entity) {
        return mapper.to(repository.save((entity)));
    }
}
