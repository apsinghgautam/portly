package com.portly.backend.services.impls;

import com.portly.backend.dto.input.SendMessageDto;
import com.portly.backend.dto.input.SendMessageFromHomeDto;
import com.portly.backend.services.ContactService;
import com.portly.backend.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final EmailService emailService;
    private final UserServiceImpl userService;

    @Override
    public String createMessage(String name, String email, String message) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Email Template</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        .email-container-wrapper {\n" +
                "            padding: 20px; /* This will add horizontal padding */\n" +
                "        }\n" +
                "        .email-container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 30px auto;\n" +
                "            background-color: #fff;\n" +
                "            border-radius: 8px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "            overflow: hidden;\n" +
                "            padding: 20px; /* Padding inside the container for content */\n" +
                "        }\n" +
                "        .header {\n" +
                "            background-color: #007BFF;\n" +
                "            color: #ffffff;\n" +
                "            text-align: center;\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "        .header h1 {\n" +
                "            margin: 0;\n" +
                "            font-size: 24px;\n" +
                "        }\n" +
                "        .content {\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "        .content h3 {\n" +
                "            margin-bottom: 10px;\n" +
                "            color: #333;\n" +
                "            font-size: 18px;\n" +
                "        }\n" +
                "        .content p {\n" +
                "            font-size: 16px;\n" +
                "            color: #555;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            padding: 20px;\n" +
                "            background-color: #007BFF;\n" +
                "            color: #ffffff;\n" +
                "            font-size: 14px;\n" +
                "        }\n" +
                "        .footer a {\n" +
                "            color: #ffffff;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"email-container-wrapper\">\n" +
                "        <div class=\"email-container\">\n" +
                "            <div class=\"header\">\n" +
                "                <h1>You've Received a New Message!</h1>\n" +
                "            </div>\n" +
                "            <div class=\"content\">\n" +
                "                <h3><strong>Name:</strong> "+ name +"</h3>\n" +
                "                <h3><strong>Email:</strong> "+ email +" </h3>\n" +
                "                <h3><strong>Message:</strong> "+ message +"</h3>\n" +
                "                <p></p>\n" +
                "            </div>\n" +
                "            <div class=\"footer\">\n" +
                "                <p>Thank you for reaching out to us! We'll be in touch soon.</p>\n" +
                "                <p><a href=\"https://profolio.live\">Visit Our Website</a></p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }

    @Override
    public String sendMessage(SendMessageDto sendMessageDto) {
        String message = createMessage(sendMessageDto.getName(),sendMessageDto.getEmail(),sendMessageDto.getMessage());
        emailService.sendEmail(userService.getUserByUri(sendMessageDto.getRecipient()).getEmail(),"New message found on your portfolio",message);
        return "Message sent successfully...";
    }

    @Override
    public String sendMessageFromHome(SendMessageFromHomeDto sendMessageDto) {
        String message = createMessage(sendMessageDto.getName(),sendMessageDto.getEmail(),sendMessageDto.getMessage());
        emailService.sendEmail("shashank1q@gmail.com","New message found on profolio page",message);
        return "Message sent successfully...";
    }
}
