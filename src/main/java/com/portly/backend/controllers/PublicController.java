package com.portly.backend.controllers;

import com.portly.backend.dto.input.SendMessageDto;
import com.portly.backend.dto.input.SendMessageFromHomeDto;
import com.portly.backend.dto.output.PortfolioDto;
import com.portly.backend.services.impls.ContactServiceImpl;
import com.portly.backend.services.impls.PortfolioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/p")
@CrossOrigin
public class PublicController {

    private final PortfolioServiceImpl portfolioService;
    private final ContactServiceImpl contactService;

    @GetMapping("/{username}")
    public ResponseEntity<PortfolioDto> getPortFolio(@PathVariable String username){
        return ResponseEntity.ok(portfolioService.getPortFolio(username));
    }

    @PostMapping("/contact")
    public ResponseEntity<String> sendMessage(@RequestBody SendMessageDto sendMessageDto){
        return ResponseEntity.ok(contactService.sendMessage(sendMessageDto));
    }

    @PostMapping("/homeContact")
    public ResponseEntity<String> sendMessage(@RequestBody SendMessageFromHomeDto sendMessageDto){
        return ResponseEntity.ok(contactService.sendMessageFromHome(sendMessageDto));
    }

}
