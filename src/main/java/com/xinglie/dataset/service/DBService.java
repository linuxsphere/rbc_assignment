package com.xinglie.dataset.service;

import com.xinglie.dataset.model.DataEntry;
import com.xinglie.dataset.model.UploadedFile;
import com.xinglie.dataset.repository.DataEntryRepository;
import com.xinglie.dataset.repository.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {

    @Autowired
    UploadedFileRepository uploadedFileRepository;

    @Autowired
    DataEntryRepository dataEntryRepository;

    public void saveUploadedRecord(UploadedFile file){
        uploadedFileRepository.save(file);
    }


    public void saveDataEntryList(List<DataEntry> dataEntryList){
        dataEntryRepository.saveAll(dataEntryList);
    }

    public DataEntry saveDataEntry(DataEntry dataEntry){
        DataEntry persisted =  dataEntryRepository.save(dataEntry);
        return persisted;
    }

    public List<DataEntry> getDataEtnries(String ticker){
        return dataEntryRepository.findByStock(ticker);
    }


}
