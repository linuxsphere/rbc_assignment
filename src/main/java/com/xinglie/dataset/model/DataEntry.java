package com.xinglie.dataset.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table( name="DataEntry")
public class DataEntry {
    @Id
    @GeneratedValue
    private BigInteger id;

    @Column
    private int quarter;
    @Column
    private String stock;
    @Column
    private String date;
    @Column
    private float open;
    @Column
    private float high;
    @Column
    private float low;
    @Column
    private float close;
    @Column
    private long volume;
    @Column
    private String percent_change_price;
    @Column
    private String percent_change_volume_over_last_wk;
    @Column
    private String previous_weeks_volume;
    @Column
    private float next_weeks_open;
    @Column
    private float next_weeks_close;
    @Column
    private float percent_change_next_weeks_price;
    @Column
    private int days_to_next_dividend;
    @Column
    private float percent_return_next_dividend;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public String getPercent_change_price() {
        return percent_change_price;
    }

    public void setPercent_change_price(String percent_change_price) {
        this.percent_change_price = percent_change_price;
    }

    public String getPercent_change_volume_over_last_wk() {
        return percent_change_volume_over_last_wk;
    }

    public void setPercent_change_volume_over_last_wk(String percent_change_volume_over_last_wk) {
        this.percent_change_volume_over_last_wk = percent_change_volume_over_last_wk;
    }

    public String getPrevious_weeks_volume() {
        return previous_weeks_volume;
    }

    public void setPrevious_weeks_volume(String previous_weeks_volume) {
        this.previous_weeks_volume = previous_weeks_volume;
    }

    public float getNext_weeks_open() {
        return next_weeks_open;
    }

    public void setNext_weeks_open(float next_weeks_open) {
        this.next_weeks_open = next_weeks_open;
    }

    public float getNext_weeks_close() {
        return next_weeks_close;
    }

    public void setNext_weeks_close(float next_weeks_close) {
        this.next_weeks_close = next_weeks_close;
    }

    public float getPercent_change_next_weeks_price() {
        return percent_change_next_weeks_price;
    }

    public void setPercent_change_next_weeks_price(float percent_change_next_weeks_price) {
        this.percent_change_next_weeks_price = percent_change_next_weeks_price;
    }

    public int getDays_to_next_dividend() {
        return days_to_next_dividend;
    }

    public void setDays_to_next_dividend(int days_to_next_dividend) {
        this.days_to_next_dividend = days_to_next_dividend;
    }

    public float getPercent_return_next_dividend() {
        return percent_return_next_dividend;
    }

    public void setPercent_return_next_dividend(float percent_return_next_dividend) {
        this.percent_return_next_dividend = percent_return_next_dividend;
    }
}
