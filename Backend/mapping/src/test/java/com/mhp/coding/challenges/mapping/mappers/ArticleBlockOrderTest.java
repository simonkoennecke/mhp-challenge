package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.mappers.ArticleMapper;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;
import com.mhp.coding.challenges.mapping.repositories.ArticleRepository;
import com.mhp.coding.challenges.mapping.services.ArticleService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArticleBlockOrderTest {
    @Test
    public void testArticleBlockOrder() {
        // TODO: mock ArticleRepository
        ArticleService service = new ArticleService(new ArticleRepository(), new ArticleMapper());
        ArticleMapper mapper = new ArticleMapper();
        ArticleDto article = service.articleForId(124L);
        ArticleBlockDto last = null;
        for (ArticleBlockDto block : article.getBlocks()) {
            if (last == null) {
                last = block;
                continue;
            }
            assertEquals("Previous block number has to be lower or equal",
                    true, last.getSortIndex() <= block.getSortIndex());
        }
    }
}
