package com.javaweb.api;

import com.javaweb.model.ProfileDTO;
import com.javaweb.service.impl.ProfileServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client/profile")
public class ProfileAPI {

    private final ProfileServiceImpl service = new ProfileServiceImpl();

    @GetMapping("/{id}")
    public ProfileDTO getProfile(@PathVariable("id") String id) {
        return service.getProfile(id);
    }
}

