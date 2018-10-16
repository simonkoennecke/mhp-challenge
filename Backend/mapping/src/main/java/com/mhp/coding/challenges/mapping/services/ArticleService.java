package com.mhp.coding.challenges.mapping.services;

import com.mhp.coding.challenges.mapping.mappers.ArticleMapper;
import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;
import com.mhp.coding.challenges.mapping.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository repository;

    private final ArticleMapper mapper;

    @Autowired
    public ArticleService(ArticleRepository repository, ArticleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ArticleDto> list() {
        final List<Article> articles = repository.all();
        return articles.stream()
                .map(mapper::map)
                .map(this::sortBlocks)
                .collect(Collectors.toList());
    }

    public ArticleDto articleForId(Long id) {
        final Article article = repository.findBy(id);
        return sortBlocks(mapper.map(article));
    }

    public ArticleDto create(ArticleDto articleDto) {
        final Article create = mapper.map(articleDto);
        repository.create(create);
        return sortBlocks(mapper.map(create));
    }

    private ArticleDto sortBlocks(ArticleDto articleDto) {
        articleDto.setBlocks(
                articleDto.getBlocks().stream()
                        .sorted(Comparator.comparingInt(ArticleBlockDto::getSortIndex))
                        .collect(Collectors.toCollection(LinkedList::new))
        );
        return articleDto;
    }
}
