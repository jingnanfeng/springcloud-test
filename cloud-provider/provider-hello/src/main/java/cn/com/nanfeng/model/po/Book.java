package cn.com.nanfeng.model.po;

import java.util.Date;

public class Book {
    private Integer bId;

    private String bName;

    private String bContent;

    private String bImage;

    private Integer bPrice;

    private Integer bNumber;

    private Date bDate;

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName == null ? null : bName.trim();
    }

    public String getbContent() {
        return bContent;
    }

    public void setbContent(String bContent) {
        this.bContent = bContent == null ? null : bContent.trim();
    }

    public String getbImage() {
        return bImage;
    }

    public void setbImage(String bImage) {
        this.bImage = bImage == null ? null : bImage.trim();
    }

    public Integer getbPrice() {
        return bPrice;
    }

    public void setbPrice(Integer bPrice) {
        this.bPrice = bPrice;
    }

    public Integer getbNumber() {
        return bNumber;
    }

    public void setbNumber(Integer bNumber) {
        this.bNumber = bNumber;
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }
}