package io.may4th.tge22.services.impl.mappers;

import io.may4th.tge22.domain.api.entities.Room;
import io.may4th.tge22.services.api.tos.NewRoomTO;
import io.may4th.tge22.services.api.tos.RoomTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper extends BaseMapper<Room, RoomTO, NewRoomTO> {
}
