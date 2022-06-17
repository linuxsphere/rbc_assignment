package com.xinglie.dataset.service;

import com.xinglie.dataset.configuation.AppTestConfiguration;
import com.xinglie.dataset.model.DataEntry;
import com.xinglie.dataset.repository.DataEntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=com.xinglie.dataset.DowJonesDatasetApp.class)
@Import(AppTestConfiguration.class)
public class FileProcessorTest {

    @Autowired
    FileProcessor fileProcessor;

    @Autowired
    DataEntryRepository dataEntryRepository;

    @Autowired
    Resource testFile;
    @Test
    public void testFileProcessing() throws Exception {
        fileProcessor.setResourceFile(testFile);
        int processed = fileProcessor.call();
        Assertions.assertEquals(750, processed);
        List<DataEntry> dataEntryList = dataEntryRepository.findByStock("XOM");
        Assertions.assertEquals(25, dataEntryList.size());
    }
}
