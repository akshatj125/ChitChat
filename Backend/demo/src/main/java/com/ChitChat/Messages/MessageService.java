package com.ChitChat.Messages;

import com.ChitChat.Messages.Messages;

import java.util.List;

public interface MessageService {

    Messages saveMessage(Messages message);

    public List<Messages> findAllMessage();

    Messages addConversationToMessage(int messageId, int conversationId);
}
