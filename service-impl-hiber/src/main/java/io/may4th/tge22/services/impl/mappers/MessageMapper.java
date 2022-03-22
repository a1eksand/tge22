package io.may4th.tge22.services.impl.mappers;

import io.may4th.tge22.domain.api.entities.Message;
import io.may4th.tge22.services.api.tos.MessageTO;
import io.may4th.tge22.services.api.tos.NewMessageTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper extends BaseMapper<Message, MessageTO, NewMessageTO> {
}
