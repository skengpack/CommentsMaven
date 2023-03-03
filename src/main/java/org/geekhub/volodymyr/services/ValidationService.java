package org.geekhub.volodymyr.services;

import org.geekhub.volodymyr.models.Comment;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ValidationService {
    Map<Integer, Comment> comments = new HashMap<>();
    AtomicInteger commentId = new AtomicInteger(0);
    boolean isEmptyName, isEmptyComment, isEmptyRating;
    String temporaryName, temporaryComment;
    int temporaryRating;

    public void validate(Comment comment) {
        isEmptyName = comment.getName().equals("");
        isEmptyComment = comment.getComment().equals("");
        isEmptyRating = comment.getRating() == 0;

        if (!isEmptyName && !isEmptyComment && !isEmptyRating) {
            comments.put(commentId.incrementAndGet(), comment);
            temporaryName = null;
            temporaryComment = null;
            temporaryRating = 0;
        } else {
            if (!isEmptyName) {
                temporaryName = comment.getName();
            } else {
                temporaryName = null;
            }

            if (!isEmptyComment) {
                temporaryComment = comment.getComment();
            } else {
                temporaryComment = null;
            }

            temporaryRating = comment.getRating();
        }
    }

    public void addAttributes(Model model) {
        model.addAttribute("comments", comments);

        model.addAttribute("emptyName", isEmptyName);
        model.addAttribute("emptyComment", isEmptyComment);
        model.addAttribute("emptyRating", isEmptyRating);

        model.addAttribute("temporaryName", temporaryName);
        model.addAttribute("temporaryComment", temporaryComment);
        model.addAttribute("temporaryRating", temporaryRating);
    }
}
