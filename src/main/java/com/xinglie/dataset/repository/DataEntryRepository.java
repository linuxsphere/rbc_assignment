package com.xinglie.dataset.repository;

import com.xinglie.dataset.model.DataEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface DataEntryRepository extends JpaRepository<DataEntry, BigInteger> {

    public List<DataEntry> findByStock(String stock);

}
