package com.xinglie.dataset.configuation;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;

@TestConfiguration
public class AppTestConfiguration {

    @Bean(name="uploadedFile")
    public Resource getUploadedFileFolder(){
        Resource testFile = new ClassPathResource("dow_jones_index.data");
        return testFile;
    }
}
