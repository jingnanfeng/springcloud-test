package cn.com.nanfeng.model.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Book implements Serializable {
    private static final long serialVersionUID = 532384274747206636L;
    private Integer bId;

    private String bName;

    private String bContent;

    private String bImage;

    private Integer bPrice;

    private Integer bNumber;

    private Date bDate;


}