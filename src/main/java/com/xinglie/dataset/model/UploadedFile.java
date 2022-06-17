package com.xinglie.dataset.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table( name="UploadedFile")
public class UploadedFile {
    @Id
    private UUID id;
    private String originalFileName;

    public UploadedFile(){

    }
    public UploadedFile(UUID uuid){
        id = uuid;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }
}
