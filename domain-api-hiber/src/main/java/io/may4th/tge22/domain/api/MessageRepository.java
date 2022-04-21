package io.may4th.tge22.domain.api;

import io.may4th.tge22.domain.api.entities.Message;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository extends PagingAndSortingRepository<Message, UUID>, QueryByExampleExecutor<Message> {

    List<Message> findAllByRoomIdOrderByInstant(UUID roomId);

    Optional<Message> findFirstByRoomIdOrderByInstantDesc(UUID roomId);
}
