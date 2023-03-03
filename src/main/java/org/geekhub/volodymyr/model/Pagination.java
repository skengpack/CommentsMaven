package org.geekhub.volodymyr.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Pagination {
    private final List<Page> pages = new ArrayList<>();

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(int numberOfAllPages, int currentPage) {
        pages.clear();

        if (currentPage == 1) {
            setFirstPageSettings(numberOfAllPages);
        } else if (currentPage == 2) {
            setSecondPageSettings(numberOfAllPages);
        } else if (currentPage > 2) {
            setThirdOrMorePageSettings(numberOfAllPages, currentPage);
        }
    }

    private void setFirstPageSettings(int numberOfAllPages) {
        pages.add(new Page(true, false, "Previous"));
        pages.add(new Page(false, true, "1"));
        if (numberOfAllPages == 1) {
            pages.add(new Page(true, false, "2"));
            pages.add(new Page(true, false, "3"));
            pages.add(new Page(true, false, "Next"));
        } else if (numberOfAllPages == 2) {
            pages.add(new Page(false, false, "2", "?page=2"));
            pages.add(new Page(true, false, "3"));
            pages.add(new Page(false, false, "Next", "?page=2"));
        } else if (numberOfAllPages > 2) {
            pages.add(new Page(false, false, "2", "?page=2"));
            pages.add(new Page(false, false, "3", "?page=3"));
            pages.add(new Page(false, false, "Next", "?page=2"));
        }
    }

    private void setSecondPageSettings(int numberOfAllPages) {
        pages.add(new Page(false, false, "Previous", "?page=1"));
        pages.add(new Page(false, false, "1", "?page=1"));
        pages.add(new Page(false, true, "2"));
        if (numberOfAllPages == 2) {
            pages.add(new Page(true, false, "3"));
            pages.add(new Page(true, false, "Next"));
        } else {
            pages.add(new Page(false, false, "3", "?page=3"));
            pages.add(new Page(false, false, "Next", "?page=3"));
        }
    }

    private void setThirdOrMorePageSettings(int numberOfAllPages, int currentPage) {
        pages.add(new Page(false, false, "Previous", "?page=" + (currentPage - 1)));
        if (currentPage == numberOfAllPages) {
            pages.add(new Page(false, false, String.valueOf(currentPage - 2),
                    "?page=" + (currentPage - 2)));
            pages.add(new Page(false, false, String.valueOf(currentPage - 1),
                    "?page=" + (currentPage - 1)));
            pages.add(new Page(false, true, String.valueOf(currentPage)));
            pages.add(new Page(true, false, "Next"));
        } else {
            pages.add(new Page(false, false, String.valueOf(currentPage - 1),
                    "?page=" + (currentPage - 1)));
            pages.add(new Page(false, true, String.valueOf(currentPage)));
            pages.add(new Page(false, false, String.valueOf(currentPage + 1),
                    "?page=" + (currentPage + 1)));
            pages.add(new Page(false, false, "Next", "?page=" + (currentPage + 1)));
        }
    }
}
