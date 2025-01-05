package com.example.jediscache;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.UnifiedJedis;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UnifiedJedis jedis;

    // redis를 사용한 cache 처리
    @GetMapping("/users/{id}/email")
    public String getUserEmail(@PathVariable long id) {
        String userEmailKey = "user:%d:email".formatted(id);

        String userEmail = jedis.get(userEmailKey);

        if(userEmail != null) {
            return userEmail;
        }

        userEmail = userRepository.findById(id).orElseThrow().getEmail();
        jedis.setex(userEmailKey, 30, userEmail);
        return userEmail;
    }
}
