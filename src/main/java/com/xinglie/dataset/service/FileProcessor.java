package com.xinglie.dataset.service;

import com.xinglie.dataset.model.DataEntry;
import com.xinglie.dataset.repository.DataEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.Buffer;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Component
public class FileProcessor implements Callable {

    @Autowired
    DataEntryRepository dataEntryRepository;

    Resource uploadedFile;

    public void setResourceFile(Resource file) {
        this.uploadedFile = file;
    }

    @Override
    public Integer call() throws Exception {
        File file = null;
        FileReader fileReader = null;
        BufferedReader reader = null;
        int count = 0;
        try {
            if (!uploadedFile.isFile() || !uploadedFile.exists())
                return -1;
            file = uploadedFile.getFile();
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            List<DataEntry> dataEntryList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {


                count++;
                if (count == 1)
                    continue; //skip first line
                DataEntry entry = new DataEntry();
                String[] values = line.split(",");
                if (values.length < 16) {
                    return -1;
                } else {

                    entry.setQuarter(Integer.parseInt(values[0]));
                    entry.setStock(values[1]);
                    entry.setDate(values[2]);
                    entry.setOpen(Float.valueOf(removeDollarSign(values[3])));
                    entry.setHigh(Float.valueOf(removeDollarSign(values[4])));
                    entry.setLow(Float.valueOf(removeDollarSign(values[5])));
                    entry.setClose(Float.valueOf(removeDollarSign(values[6])));
                    entry.setVolume(Long.valueOf(values[7]));
                    entry.setPercent_change_price(values[8]);
                    entry.setPercent_change_volume_over_last_wk(values[9]);
                    entry.setPrevious_weeks_volume(values[10]);
                    entry.setNext_weeks_open(Float.valueOf(removeDollarSign(values[11])));
                    entry.setNext_weeks_close(Float.valueOf(removeDollarSign(values[12])));
                    entry.setPercent_change_next_weeks_price(Float.valueOf(values[13]));
                    entry.setDays_to_next_dividend(Integer.parseInt(values[14]));
                    entry.setPercent_return_next_dividend(Float.valueOf(values[15]));
                    dataEntryList.add(entry);
                }

            }
            dataEntryRepository.saveAll(dataEntryList);
        } catch (Exception e) {
            System.err.println("failed to process file: " + this.uploadedFile.getFilename());
        } finally{
            if (reader != null) reader.close();
            if (fileReader != null) fileReader.close();
        }

        return count-1;
    }

    protected String removeDollarSign(String value) {
        return value.replaceAll("[^0-9.]+", "");

    }
}
