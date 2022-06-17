package com.xinglie.dataset.repository;

import com.xinglie.dataset.model.DataEntry;
import com.xinglie.dataset.repository.DataEntryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=com.xinglie.dataset.DowJonesDatasetApp.class)
public class DataEntryRepositoryTest {
    @Autowired
    DataEntryRepository dataEntryRepository;

    @Test
    public void testDataEntrySave(){
        DataEntry entry = new DataEntry();
        entry.setQuarter(Integer.parseInt("1"));
        entry.setStock("MyTicker");
        entry.setDate("1/7/2011");
        entry.setOpen(Float.valueOf("101.1"));
        entry.setHigh(Float.valueOf("1001.33"));
        entry.setLow(Float.valueOf("99.1"));
        entry.setClose(Float.valueOf("999.33"));
        entry.setVolume(Long.valueOf("239655616"));
        entry.setPercent_change_price("3.332232");
        entry.setPercent_change_volume_over_last_wk("");
        entry.setPrevious_weeks_volume("");
        entry.setNext_weeks_open(Float.valueOf("12232.33"));
        entry.setNext_weeks_close(Float.valueOf("12123.33"));
        entry.setPercent_change_next_weeks_price(Float.valueOf("33.3233"));
        entry.setDays_to_next_dividend(Integer.parseInt("26"));
        entry.setPercent_return_next_dividend(Float.valueOf("33.133"));
        DataEntry repoDataEntry = dataEntryRepository.save(entry);
        List<DataEntry> persisted = dataEntryRepository.findByStock("MyTicker");
        Assert.assertEquals(1, persisted.size());
        Assert.assertEquals("MyTicker", persisted.get(0).getStock());
        dataEntryRepository.delete(repoDataEntry);

    }
}
