package com.lacazaduweb.whatsapp.reader.adapter;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class WebhookController {

	@PostMapping("/v1/webhook")
	public ResponseEntity<String> getMessage(@RequestBody String payload) {
		log.info("Payload received: {}", payload);
		return ResponseEntity.ok("OK");
	}
}