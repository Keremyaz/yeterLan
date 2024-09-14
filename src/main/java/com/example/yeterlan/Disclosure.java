package com.example.yeterlan;

public class Disclosure {
    private String disclosureIndex;//disclosureId
    private String publishDate;//disclosureDate
    private String title;
    private String companyTitle;//company
    private String disclosureType;
    private String attachmentCount;
    private String relatedStocks;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDisclosureIndex() {
        return disclosureIndex;
    }

    public void setDisclosureIndex(String disclosureIndex) {
        this.disclosureIndex = disclosureIndex;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public String getDisclosureType() {
        return disclosureType;
    }

    public void setDisclosureType(String disclosureType) {
        this.disclosureType = disclosureType;
    }

    public String getAttachmentCount() {
        return attachmentCount;
    }

    public void setAttachmentCount(String attachmentCount) {
        this.attachmentCount = attachmentCount;
    }

    public String getRelatedStocks() {
        return relatedStocks;
    }

    public void setRelatedStocks(String relatedStocks) {
        this.relatedStocks = relatedStocks;
    }
}