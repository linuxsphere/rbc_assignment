package com.xinglie.dataset.repository;

import com.xinglie.dataset.model.UploadedFile;
import com.xinglie.dataset.repository.UploadedFileRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=com.xinglie.dataset.DowJonesDatasetApp.class)
public class UploadedFileRepositoryTest {


    @Autowired
    UploadedFileRepository uploadedFileRepository;

    @Test
    public void testUploadedFilePersistence(){

        UUID testID = UUID.randomUUID();
        UploadedFile file = new UploadedFile(testID);
        file.setOriginalFileName("C:\\test\fileName1.data");
        UploadedFile savedFile = uploadedFileRepository.save(file);
        Assertions.assertEquals(testID, savedFile.getId());
        Assertions.assertEquals(savedFile.getOriginalFileName(),"C:\\test\fileName1.data" );


    }
}
