package ovaphlow.pitchfork.spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovaphlow.pitchfork.spr.entity.User;
import ovaphlow.pitchfork.spr.mapper.UserMapper;

import java.util.List;

@RestController // 控制器，处理客户端/浏览器发送的请求
@RequestMapping("/api/simple") // 请求地址的前缀
public class UserController {

    @Autowired // 注入
    UserMapper userMapper;

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> filter(@RequestParam(value = "take", defaultValue = "10") Long take,
                                             @RequestParam(value = "skip", defaultValue = "0") Long skip) {
        return ResponseEntity.status(200).body(userMapper.filter(take, skip));
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> filter(@RequestBody User user) {
        List<User> userList = userMapper.filterByName(user.getName());
        if (userList.size() > 0) return ResponseEntity.status(400).build();
        user.setSalt("12345678");
        user.setTag("{}");
        userMapper.save(user);
        return ResponseEntity.status(201).build();
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

    @RequestMapping(path = "/user/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User body) {
        List<User> userList = userMapper.filterByName(body.getName());
        if (userList.size() == 0) return ResponseEntity.status(401).build();
        User user = userList.get(0);
        if (user.getPassword().equals(body.getPassword())) {
            user.setPassword(null);
            user.setSalt(null);
            return ResponseEntity.status(200).body(user);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

}
