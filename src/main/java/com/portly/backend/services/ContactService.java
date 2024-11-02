package com.portly.backend.services;

import com.portly.backend.dto.input.SendMessageDto;
import com.portly.backend.dto.input.SendMessageFromHomeDto;

public interface ContactService {

    String createMessage(String name, String email, String message);

    String sendMessage(SendMessageDto sendMessageDto);

    String sendMessageFromHome(SendMessageFromHomeDto sendMessageDto);
}
