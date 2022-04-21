package io.may4th.tge22.web.services;

import io.may4th.tge22.services.api.RoomService;
import io.may4th.tge22.services.api.tos.GetCreatedUserId;
import io.may4th.tge22.services.api.tos.RoomTO;
import io.may4th.tge22.web.security.UserDetailsImpl;
import io.may4th.tge22.web.services.exceptions.PermissionDeniedException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@AllArgsConstructor
@Service
public class PermissionService {

    @Autowired
    private final RoomService roomService;

    public void validateRoomPermission(UserDetailsImpl currentUser, UUID roomId) {
        Optional
            .of(roomId)
            .map(roomService::findById)
            .map(RoomTO::getUsers)
            .filter(users -> users.containsValue(currentUser.getUuid()))
            .orElseThrow(PermissionDeniedException::new);
    }

    public void validateCreatedByCurrentUser(UserDetailsImpl currentUser, GetCreatedUserId idHolder) {
        Optional
            .of(idHolder)
            .map(GetCreatedUserId::getCreatedUserId)
            .filter(uid -> test(uid, currentUser::getUuid))
            .orElseThrow(PermissionDeniedException::new);
    }

    private boolean test(UUID uuid, Supplier<UUID> supplier) {
        return Objects.equals(uuid, supplier.get());
    }
}
