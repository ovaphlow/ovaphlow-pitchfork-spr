package ovaphlow.pitchfork.spr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovaphlow.pitchfork.spr.entity.Document;
import ovaphlow.pitchfork.spr.mapper.DocumentMapper;
import ovaphlow.pitchfork.spr.utility.Snowflake;

import java.util.List;

@RestController
@RequestMapping("/api/simple/biz")
public class DocumentController {

    private final DocumentMapper documentMapper;

    public DocumentController(DocumentMapper documentMapper) {
        this.documentMapper = documentMapper;
    }

    @RequestMapping(path = "/document/{id}", method = RequestMethod.GET)
    public ResponseEntity<Document> get(@PathVariable("id") Long id) {
        Document result = documentMapper.filterById(id);
        return ResponseEntity.status(200).body(result);
    }

    @RequestMapping(path = "/document/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Document> update(@PathVariable("id") Long id, @RequestBody Document document) {
        document.setId(id);
        documentMapper.update(document);
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/document/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Document> remove(@PathVariable("id") Long id) {
        documentMapper.remove(id);
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/document", method = RequestMethod.GET)
    public ResponseEntity<List<Document>> filter(@RequestParam(value = "option", defaultValue = "") String option,
                                                 @RequestParam(value = "skip", defaultValue = "0") int skip,
                                                 @RequestParam(value = "take", defaultValue = "10") int take) {
        if ("".equals(option)) {
            List<Document> result = documentMapper.filter(skip, take);
            return ResponseEntity.status(200).body(result);
        }
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/document", method = RequestMethod.POST)
    public ResponseEntity<Document> save(@RequestBody Document body) {
        Snowflake flakeId = new Snowflake(1, 1, 1);
        body.setId(flakeId.nextId());
        System.out.println(body);
        documentMapper.save(body);
        return ResponseEntity.status(201).build();
    }

}
