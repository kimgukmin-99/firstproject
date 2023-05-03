package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j //로깅
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){

        //로깅
        log.info(form.toString());
        //변환
        Article article = form.toEntity();
        log.info(article.toString());
        //저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        //id로 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //데이터 모델등록
        model.addAttribute("article", articleEntity);

        //페이지 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //모든 아티클 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        //가져온 아티클 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);
        //뷰 페이지 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        Article articleEntity = articleRepository.findById(id).orElse(null);
        //데이터 모델등록
        model.addAttribute("article", articleEntity);
        //수정할 데이터 가져오기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if (target != null){
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/" + articleEntity.getId();
    }
    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("Delete");
        Article target = articleRepository.findById(id).orElse(null);

        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "Delete Complete");

        }

        return "redirect:/articles";
    }
}
