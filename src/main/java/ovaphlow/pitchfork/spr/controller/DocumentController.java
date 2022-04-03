package ovaphlow.pitchfork.spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovaphlow.pitchfork.spr.entity.Document;
import ovaphlow.pitchfork.spr.mapper.DocumentMapper;

import java.util.List;

@RestController
@RequestMapping("/api/simple/biz")
public class DocumentController {

    @Autowired
    DocumentMapper documentMapper;

    @RequestMapping(path = "/document/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("id") Long id) {
        Document result = documentMapper.filterById(id);
        return ResponseEntity.status(200).body(result);
    }

    @RequestMapping(path = "/document/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Document document) {
        document.setId(id);
        documentMapper.update(document);
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/document/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable("id") Long id) {
        documentMapper.remove(id);
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/document", method = RequestMethod.GET)
    public ResponseEntity filter(@RequestParam(value = "option", defaultValue = "") String option,
                                 @RequestParam(value = "skip", defaultValue = "0") int skip,
                                 @RequestParam(value = "take", defaultValue = "10") int take) {
        if ("".equals(option)) {
            List<Document> result = documentMapper.filter(skip, take);
            return ResponseEntity.status(200).body(result);
        }
        return ResponseEntity.status(200).body("OK");
    }

    @RequestMapping(path = "/document", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody Document document) {
        documentMapper.save(document);
        return ResponseEntity.status(201).build();
    }

}
