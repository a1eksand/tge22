package io.may4th.tge22.services.impl;

import io.may4th.tge22.common.Color;
import io.may4th.tge22.domain.api.RoomRepository;
import io.may4th.tge22.domain.api.entities.Room;
import io.may4th.tge22.services.api.RoomService;
import io.may4th.tge22.services.api.tos.NewRoomTO;
import io.may4th.tge22.services.api.tos.RoomTO;
import io.may4th.tge22.services.impl.mappers.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomServiceImpl extends BaseService<Room, RoomTO, NewRoomTO> implements RoomService {

    @Autowired
    private final RoomMapper roomMapper;
    @Autowired
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomMapper roomMapper, RoomRepository roomRepository) {
        super(roomMapper, roomRepository);
        this.roomMapper = roomMapper;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public RoomTO save(NewRoomTO newRoomTO) {
        var room = roomMapper.en(newRoomTO);
        room.getUsers().put(Color.WHITE, room.getUserId());
        return saveOrUpdate(room);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomTO> findAllByUsersId(UUID userId) {
        return roomMapper.to(roomRepository.findAllByUsersOrderByInstant(userId));
    }

    @Override
    @Transactional
    public Map<Color, UUID> joinUserToRoom(UUID userId, UUID roomId) {
        var room = roomRepository.findById(roomId).orElseThrow(() -> notFound(roomId));
        Optional
            .of(room)
            .map(Room::getUsers)
            .filter(users -> users.size() == 1)
            .filter(users -> !users.containsValue(userId))
            .orElseThrow(IllegalStateException::new)
            .put(Color.BLACK, userId);
        roomRepository.save(room);
        return room.getUsers();
    }
}
