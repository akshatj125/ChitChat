package com.ChitChat.ReceivedMessages;

import java.util.List;

public interface ReceivedMessagesService {

    ReceivedMessages save(ReceivedMessages receivedMessages);

    List<ReceivedMessages> findAll();
}
