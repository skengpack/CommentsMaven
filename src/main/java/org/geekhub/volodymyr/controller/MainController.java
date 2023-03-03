package org.geekhub.volodymyr.controller;

import org.geekhub.volodymyr.model.Comment;
import org.geekhub.volodymyr.service.CommentsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private final CommentsService commentsService;

    public MainController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/")
    public String showComments(Model model, @RequestParam(required = false, defaultValue = "1") int page) {
        commentsService.addAttributes(model, page);
        return "comments";
    }

    @PostMapping("/")
    public String addComment(@ModelAttribute Comment comment) {
        commentsService.addComment(comment);
        int page = commentsService.getNumberOfAllPages();
        return ("redirect:/?page=" + page);
    }
}
