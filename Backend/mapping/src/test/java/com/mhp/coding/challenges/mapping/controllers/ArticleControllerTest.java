package com.mhp.coding.challenges.mapping.controllers;

import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.services.ArticleService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArticleControllerTest {

    private ArticleService service;

    private ArticleController controller;

    @Before
    public void setUp() {
        service = mock(ArticleService.class);
        controller = new ArticleController(service);
    }

    @Test
    public void testArticleFound() {
        Long articleId = 124L;
        when(service.articleForId(eq(articleId))).thenReturn(new ArticleDto());
        ResponseEntity<ArticleDto> response = controller.details(articleId);
        assertEquals("Status 200", HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testArticleNotFound() {
        when(service.articleForId(any(Long.class))).thenReturn(null);
        ResponseEntity<ArticleDto> response = controller.details(456L);
        assertEquals("Status 404 not found", HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
