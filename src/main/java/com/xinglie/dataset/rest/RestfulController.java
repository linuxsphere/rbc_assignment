package com.xinglie.dataset.rest;

import com.xinglie.dataset.model.DataEntry;
import com.xinglie.dataset.model.UploadedFile;
import com.xinglie.dataset.service.DBService;
import com.xinglie.dataset.service.FileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
@RequestMapping("dowjones/v1")
public class RestfulController {

    @Autowired
    File uploadedFileFolder;

    @Autowired
    DBService dbService;

    @Autowired
    FileProcessor fileProcessor;

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    @PostMapping(value="/upload", consumes=MediaType.MULTIPART_FORM_DATA_VALUE, produces=MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> uploadFile(@RequestPart(name="file") MultipartFile file) {
        if (null == file.getOriginalFilename()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            byte[] bytes = file.getBytes();
            UUID uuid = UUID.randomUUID();
            UploadedFile uploadedFile = new UploadedFile(uuid);
            uploadedFile.setOriginalFileName(file.getOriginalFilename());
            Path path = Paths.get(uploadedFileFolder.getPath(), String.valueOf(uuid));
            Files.write(path, bytes);
            dbService.saveUploadedRecord(uploadedFile);
            fileProcessor.setResourceFile(new FileSystemResource(path.toFile()));
            Future<Integer> future = executorService.submit(fileProcessor);
            Integer result = future.get();
            return new ResponseEntity<>("File uploaded with total records " + result , HttpStatus.OK);
        } catch (IOException | InterruptedException | ExecutionException e) {
            return new ResponseEntity<>("Upload failed" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping(path="/stock/{ticker}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<DataEntry> getStockInfo(@PathVariable String ticker){
        List<DataEntry> dataEntryList =  dbService.getDataEtnries(ticker);
        return dataEntryList;
    }

    @PostMapping(path="/stock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> postDataEntry(@RequestBody DataEntry dataEntry){
        DataEntry dataEntry1 = dbService.saveDataEntry(dataEntry);
        return new ResponseEntity<>("Success" , HttpStatus.OK);

    }
}
