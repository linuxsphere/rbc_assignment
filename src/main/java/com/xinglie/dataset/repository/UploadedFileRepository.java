package com.xinglie.dataset.repository;

import com.xinglie.dataset.model.DataEntry;
import com.xinglie.dataset.model.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.UUID;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, UUID> {
}
