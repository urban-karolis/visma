package visma.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
//    @CsvBindByName
//    private String name2;

    @CsvBindByName(column = "Item Name", required = true)
    private String name;

    @CsvBindByName(column = "Code")
    private String id;

    @CsvBindByName(column = "Quantity")
    private int qty;

    @CsvBindByName(column = "Expiration Date")
    @CsvDate(value="yyyy-MM-dd")
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}


