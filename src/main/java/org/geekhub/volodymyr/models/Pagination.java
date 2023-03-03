package org.geekhub.volodymyr.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Pagination {
    private final List<Page> pages = new ArrayList<>();

    public void setPages(int numberOfAllPages, int currentPage) {
        pages.clear();

        if (currentPage == 1) {
            pages.add(new Page(true, false, "Previous"));
            pages.add(new Page(false, true, "1"));
            if (numberOfAllPages == 1) {
                pages.add(new Page(true, false, "2"));
                pages.add(new Page(true, false, "3"));
                pages.add(new Page(true, false, "Next"));
            }
            if (numberOfAllPages == 2) {
                pages.add(new Page(false, false, "2", "?page=2"));
                pages.add(new Page(true, false, "3"));
                pages.add(new Page(false, false, "Next", "?page=2"));
            }
            if (numberOfAllPages >= 3) {
                pages.add(new Page(false, false, "2", "?page=2"));
                pages.add(new Page(false, false, "3", "?page=3"));
                pages.add(new Page(false, false, "Next", "?page=2"));
            }
        }

        if (currentPage == 2) {
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

        if (currentPage > 2) {
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
                pages.add(new Page(false, false, String.valueOf(currentPage),
                        "?page=" + (currentPage + 1)));
                pages.add(new Page(false, false, "Next", "?page=" + (currentPage + 1)));
            }
        }
    }

    public List<Page> getPages() {
        return pages;
    }
}
