package ovaphlow.pitchfork.spr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/simple")
public class FileController {

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ResponseEntity<String> upload(@RequestBody MultipartFile file) throws IOException {
        String oldName = file.getOriginalFilename();
        String path = "D:\\upload_files\\123456\\";
        String filePath = path + oldName;
        File newFile = new File(filePath);
        if (oldName == null) {
            return ResponseEntity.status(401).build();
        }
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        file.transferTo(newFile);
        return ResponseEntity.status(200).build();
    }

}
