package ovaphlow.pitchfork.spr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovaphlow.pitchfork.spr.entity.User;

import ovaphlow.pitchfork.spr.mapper.UserMapper;
import ovaphlow.pitchfork.spr.utility.SecureText;
import ovaphlow.pitchfork.spr.utility.ShaUtility;
import ovaphlow.pitchfork.spr.utility.Snowflake;

import java.util.List;

@RestController
@RequestMapping("/api/simple")
public class UserController {

    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @RequestMapping(path = "/user/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User body) {

        List<User> userList = userMapper.filterByName(body.getName());
        if (userList.size() == 0) return ResponseEntity.status(401).build();
        User user = userList.get(0);
        String saltedPassword = ShaUtility.SHA256(body.getPassword() + user.getSalt());
        if (user.getPassword().equals(saltedPassword)) {
            user.setPassword(null);
            user.setSalt(null);
            return ResponseEntity.status(200).body(user);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable("id") Long id) {
        User user = userMapper.filterById(id);
        user.setPassword(null);
        user.setSalt(null);
        return ResponseEntity.status(200).body(user);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody User body) {
        User user = userMapper.filterById(id);
        user.setName(body.getName());
        user.setPhone(body.getPhone());
        user.setDept(body.getDept());
        userMapper.update(user);
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove(@PathVariable("id") Long id) {
        userMapper.remove(id);
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> filter(@RequestParam(value = "take", defaultValue = "10") Long take,
                                             @RequestParam(value = "skip", defaultValue = "0") Long skip) {
        return ResponseEntity.status(200).body(userMapper.filter(take, skip));
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> filter(@RequestBody User user) {
        List<User> userList = userMapper.filterByName(user.getName());
        if (userList.size() > 0) return ResponseEntity.status(400).build();
        Snowflake flakeId = new Snowflake(1, 1, 1);
        user.setId(flakeId.nextId());
        user.setSalt(SecureText.getSecureText());
        user.setPassword(ShaUtility.SHA256(user.getPassword() + user.getSalt()));
        user.setTag("[]");
        userMapper.save(user);
        return ResponseEntity.status(201).build();
    }



}
