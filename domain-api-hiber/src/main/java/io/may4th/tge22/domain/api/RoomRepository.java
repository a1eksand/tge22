package io.may4th.tge22.domain.api;

import io.may4th.tge22.domain.api.entities.Room;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.UUID;

public interface RoomRepository extends PagingAndSortingRepository<Room, UUID>, QueryByExampleExecutor<Room> {

    List<Room> findAllByUsersOrderByInstant(UUID usersId);
}
