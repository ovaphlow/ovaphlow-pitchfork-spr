package ovaphlow.pitchfork.spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovaphlow.pitchfork.spr.entity.User;
import ovaphlow.pitchfork.spr.mapper.UserMapper;

@RestController
@RequestMapping("/api/simple")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public ResponseEntity filter(@RequestParam(value = "take", defaultValue = "10") Long take,
                                 @RequestParam(value = "skip", defaultValue = "0") Long skip) {
        return ResponseEntity.status(200).body(userMapper.filter(take, skip));
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity filter(@RequestBody User user) {
        userMapper.save(user);
        return ResponseEntity.status(201).build();
    }
}
