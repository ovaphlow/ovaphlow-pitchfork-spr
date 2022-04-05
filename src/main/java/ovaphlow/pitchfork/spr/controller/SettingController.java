package ovaphlow.pitchfork.spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovaphlow.pitchfork.spr.entity.Setting;
import ovaphlow.pitchfork.spr.mapper.SettingMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/simple")
public class SettingController {

    @Autowired
    SettingMapper settingMapper;

    @RequestMapping(path = "/setting/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("id") Long id) {
        Setting setting = settingMapper.filterById(id);
        return ResponseEntity.status(200).body(setting);
    }

    @RequestMapping(path = "/setting/{id}", method = RequestMethod.PUT)
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Setting setting) {
        setting.setId(id);
        settingMapper.update(setting);
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/setting/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        settingMapper.remove(id);
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public ResponseEntity filter(@RequestParam(value = "option", defaultValue = "") String option,
                                 @RequestParam(value = "take", defaultValue = "10") Long take,
                                 @RequestParam(value = "skip", defaultValue = "0") Long skip,
                                 HttpServletRequest request) {
        if ("".equals(option)) {
            List<Setting> settingList = settingMapper.filter(take, skip);
            return ResponseEntity.status(200).body(settingList);
        } else if ("filterBy-refId".equals(option)) {
            Long refId = Long.parseLong(request.getParameter("refId"), 10);
            Setting setting = new Setting();
            setting.setRefId(refId);
            List<Setting> settingList = settingMapper.filterByRefId(setting);
            return ResponseEntity.status(200).body(settingList);
        }
        return ResponseEntity.status(200).body(new ArrayList<>());
    }

    @RequestMapping(path = "/setting", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Setting setting) {
        settingMapper.save(setting);
        return ResponseEntity.status(201).build();
    }
}
