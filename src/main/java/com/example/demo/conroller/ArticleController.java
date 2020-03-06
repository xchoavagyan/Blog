package com.example.demo.conroller;

import com.example.demo.models.Article;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/article")
    public void create(@RequestBody Article article) {
        articleService.create(article);
    }

    @GetMapping(value = "/article/{id1}/user/{id2}")
    public void initializeArticle(@PathVariable(value = "id1") int articleId, @PathVariable(value = "id2") int userId) {
        System.out.println(articleService.addUserToArticle(articleId, userId));
    }

    @GetMapping(value = "/article/{id}")
    public ResponseEntity<List<Article>> findAllUserArticles(@PathVariable(value = "id") int userId) {
        List<Article> allByUser = articleService.findAllByUser(userId);
        return ResponseEntity.ok(allByUser);
    }
}
