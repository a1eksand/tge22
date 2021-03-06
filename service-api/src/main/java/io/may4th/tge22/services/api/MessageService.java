package io.may4th.tge22.services.api;

import io.may4th.tge22.services.api.tos.MessageTO;
import io.may4th.tge22.services.api.tos.NewMessageTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageService extends BaseService<MessageTO, NewMessageTO> {

    List<MessageTO> findAllByRoomId(UUID roomId);

    Optional<MessageTO> findLastByRoomId(UUID roomId);
}
