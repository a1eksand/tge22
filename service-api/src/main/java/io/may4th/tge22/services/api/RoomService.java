package io.may4th.tge22.services.api;

import io.may4th.tge22.services.api.tos.NewRoomTO;
import io.may4th.tge22.services.api.tos.RoomTO;

import java.util.List;
import java.util.UUID;

public interface RoomService extends BaseService<RoomTO, NewRoomTO> {

    List<RoomTO> findAllByUsersId(UUID uuid);

    void joinUserToRoom(UUID userId, UUID roomId);
}
