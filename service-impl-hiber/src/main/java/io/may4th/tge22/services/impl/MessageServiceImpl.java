package io.may4th.tge22.services.impl;

import io.may4th.tge22.domain.api.MessageRepository;
import io.may4th.tge22.domain.api.entities.Message;
import io.may4th.tge22.services.api.MessageService;
import io.may4th.tge22.services.api.tos.MessageTO;
import io.may4th.tge22.services.api.tos.NewMessageTO;
import io.may4th.tge22.services.impl.mappers.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageServiceImpl extends BaseService<Message, MessageTO, NewMessageTO> implements MessageService {

    @Autowired
    private final MessageMapper messageMapper;
    @Autowired
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageMapper messageMapper, MessageRepository messageRepository) {
        super(messageMapper, messageRepository);
        this.messageMapper = messageMapper;
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageTO> findAllByRoomId(UUID roomId) {
        return messageMapper.to(messageRepository.findAllByRoomIdOrderByInstant(roomId));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MessageTO> findLastByRoomId(UUID roomId) {
        return messageRepository
            .findFirstByRoomIdOrderByInstantDesc(roomId)
            .map(messageMapper::to);
    }

    @Override
    @Transactional
    public MessageTO save(NewMessageTO newMessageTO) {
        return saveNew(newMessageTO);
    }
}
