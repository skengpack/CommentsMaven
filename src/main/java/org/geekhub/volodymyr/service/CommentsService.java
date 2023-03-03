package org.geekhub.volodymyr.service;

import org.geekhub.volodymyr.model.Comment;
import org.geekhub.volodymyr.model.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class CommentsService {
    public static final int COMMENTS_PER_ONE_PAGE = 2;
    private final Map<Integer, Comment> comments = new HashMap<>();
    private final AtomicInteger commentId = new AtomicInteger(0);
    private final Pagination pagination;

    public CommentsService(Pagination pagination) {
        this.pagination = pagination;
    }

    public void addComment(Comment comment) {
        comments.put(commentId.incrementAndGet(), comment);
    }

    public void addAttributes(Model model, int currentPage) {
        if (!comments.isEmpty()) {
            int numberOfAllPages = getNumberOfAllPages();

            if (currentPage < 1 || currentPage > numberOfAllPages) {
                currentPage = 1;
                model.addAttribute("pagingError", true);
            } else model.addAttribute("pagingError", false);

            model.addAttribute("comments", getCommentsListOnCurrentPage(currentPage));

            pagination.setPages(numberOfAllPages, currentPage);
            model.addAttribute("pagination", pagination.getPages());
        }
    }

    public int getNumberOfAllPages() {
        int numberOfAllPages;

        int numberOfComments = comments.size();
        if (numberOfComments % COMMENTS_PER_ONE_PAGE == 0) {
            numberOfAllPages = numberOfComments / COMMENTS_PER_ONE_PAGE;
        } else {
            numberOfAllPages = numberOfComments / COMMENTS_PER_ONE_PAGE + 1;
        }
        return numberOfAllPages;
    }

    private List<Comment> getCommentsListOnCurrentPage(int page) {
        int startId = (page - 1) * COMMENTS_PER_ONE_PAGE + 1;
        int endId = Math.min(page * COMMENTS_PER_ONE_PAGE, comments.size());

        return comments.entrySet()
                .stream()
                .filter(entry -> entry.getKey() >= startId && entry.getKey() <= endId)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
