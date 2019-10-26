package cn.com.nanfeng.model.po;

import lombok.Data;

import java.util.Date;

@Data
public class Book {
    private Integer bId;

    private String bName;

    private String bContent;

    private String bImage;

    private Integer bPrice;

    private Integer bNumber;

    private Date bDate;


}