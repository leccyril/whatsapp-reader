package com.lacazaduweb.whatsapp.reader.adapter;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api")
public class WebhookController {

    @Value("${meta.webhook.verify-token}")
    private String verifyToken;

    /**
     * Handles webhook verification requests from Meta.
     * When Meta validates your webhook endpoint, it sends a GET request with
     * mode=subscribe, verify_token, and challenge parameters.
     */
    @GetMapping("/v1/webhook")
    public ResponseEntity<String> verifyWebhook(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.verify_token") String token,
            @RequestParam("hub.challenge") String challenge) {

        log.info("Received webhook verification request: mode={}, token={}, challenge={}", mode, token, challenge);

        // Verify that the mode is 'subscribe' and the token matches our verify token
        if ("subscribe".equals(mode) && verifyToken.equals(token)) {
            log.info("Webhook verification successful");
            // Respond with the challenge to confirm verification
            return ResponseEntity.ok(challenge);
        } else {
            log.warn("Webhook verification failed - invalid token or mode");
            return ResponseEntity.badRequest().body("Verification failed");
        }
    }

    @PostMapping("/v1/webhook")
    public ResponseEntity<String> getMessage(@RequestBody String payload) {
        log.info("Payload received: {}", payload);
        return ResponseEntity.ok("OK");
    }
}