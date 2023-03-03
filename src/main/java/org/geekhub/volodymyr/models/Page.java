package org.geekhub.volodymyr.models;

public class Page {
    private String visibilityParameter;
    private final String pageName;
    private String pageLink;

    public Page(boolean isDisabled, boolean isActive, String pageName, String pageLink) {
        setVisibilityParameter(isDisabled, isActive);
        this.pageName = pageName;
        this.pageLink = pageLink;
    }

    public Page(boolean isDisabled, boolean isActive, String pageName) {
        setVisibilityParameter(isDisabled, isActive);
        this.pageName = pageName;
        if (isDisabled || isActive) {
            pageLink = "#";
        }
    }

    public String getPageName() {
        return pageName;
    }

    public String getPageLink() {
        return pageLink;
    }

    public String getVisibilityParameter() {
        return visibilityParameter;
    }

    private void setVisibilityParameter(boolean isDisabled, boolean isActive) {
        if (isDisabled) {
            visibilityParameter = "page-item disabled";
        } else if (isActive) {
            visibilityParameter = "page-item active\" aria-current=\"page";
        } else {
            visibilityParameter = "page-item";
        }
    }
}
