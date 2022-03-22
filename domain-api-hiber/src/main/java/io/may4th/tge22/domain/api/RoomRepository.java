package io.may4th.tge22.domain.api;

import io.may4th.tge22.domain.api.entities.Room;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.UUID;

public interface RoomRepository extends PagingAndSortingRepository<Room, UUID>, QueryByExampleExecutor<Room> {
}
