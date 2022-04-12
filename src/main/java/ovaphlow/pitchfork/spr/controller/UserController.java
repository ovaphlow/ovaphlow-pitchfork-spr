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
    public ResponseEntity filter(@RequestParam(value = "take", defaultValue = "10") Long take,
                                 @RequestParam(value = "skip", defaultValue = "0") Long skip) {
        return ResponseEntity.status(200).body(userMapper.filter(take, skip));
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity filter(@RequestBody User user) {
        userMapper.save(user);
        return ResponseEntity.status(201).build();
    }

    @RequestMapping(path = "/user/{id}" ,method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") Long id ,@RequestBody User user){
        user.setId(id);
        userMapper.update(user);
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("id") Long id) {
        userMapper.remove(id);
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "user/login", method = RequestMethod.GET)
    public ResponseEntity login(@RequestBody User user){

       String name = user.getName();
       String passWord = userMapper.login(name);

       String passWord1 = user.getPassword();

       if (passWord1.equals(passWord)){
           return ResponseEntity.status(200).build();
       }else {
           return ResponseEntity.status(401).build();
       }
    }

}
