package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.db.Image;
import com.mhp.coding.challenges.mapping.models.db.ImageSize;
import com.mhp.coding.challenges.mapping.models.db.blocks.*;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlockDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlockDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlockDto;
import com.mhp.coding.challenges.mapping.repositories.ArticleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Dao2DtoMappperTest {

    private ArticleRepository repository;

    private ArticleMapper mapper;

    private Article article;

    @Before
    public void setUp() {
        mapper = new ArticleMapper();
        repository = new ArticleRepository();
        // TODO: create test data to avoid the dependency to repo
        List<Article> list = repository.all();
        article = list.get(0);
    }

    @Test
    public void testArticleFields() {
        ArticleDto dto = mapper.map(article);
        assertEquals("ID have to match", article.getId(), dto.getId());
        assertEquals("description have to match", article.getDescription(), dto.getDescription());
        assertEquals("Author have to match", article.getAuthor(), dto.getAuthor());
        assertEquals("Title have to match", article.getTitle(), dto.getTitle());
    }


    @Test
    public void testArticleBlocksGallery() {
        Image image = createImage();
        List<Image> list = new LinkedList<>();
        list.add(image);

        GalleryBlock block1 = new GalleryBlock();
        block1.setSortIndex(12);
        block1.setImages(list);
        article.setBlocks(Sets.newSet(block1));
        ArticleDto dto = mapper.map(article);
        GalleryBlockDto blockDto = (GalleryBlockDto) dto.getBlocks().iterator().next();
        assertEquals("GalleryBlock -> Image -> Url", image.getUrl(),
                blockDto.getImages().get(0).getUrl());
    }

    @Test
    public void testArticleBlocksText() {
        TextBlock block1 = new TextBlock();
        block1.setSortIndex(2);
        block1.setText("Hello World!");
        article.setBlocks(Sets.newSet(block1));
        ArticleDto dto = mapper.map(article);
        TextBlockDto blockDto = (TextBlockDto) dto.getBlocks().iterator().next();
        assertEquals("TextBlock -> Text", block1.getText(),
                blockDto.getText());
    }

    @Test
    public void testArticleBlocksVideo() {
        VideoBlock block1 = new VideoBlock();
        block1.setSortIndex(2);
        block1.setUrl("http://video/url");
        block1.setType(VideoBlockType.YOUTUBE);
        article.setBlocks(Sets.newSet(block1));
        ArticleDto dto = mapper.map(article);
        VideoBlockDto blockDto = (VideoBlockDto) dto.getBlocks().iterator().next();
        assertEquals("VideoBlock Url", block1.getUrl(),
                blockDto.getUrl());
        assertEquals("VideoBlock Type", block1.getType(),
                blockDto.getType());
    }

    @Test
    public void testArticleBlocksImage() {
        Image image = createImage();
        ImageBlock block1 = new ImageBlock();
        block1.setSortIndex(2);
        block1.setImage(image);
        article.setBlocks(Sets.newSet(block1));
        ArticleDto dto = mapper.map(article);
        ImageBlockDto blockDto = (ImageBlockDto) dto.getBlocks().iterator().next();
        assertEquals("Image Url", image.getUrl(), blockDto.getImage().getUrl());
        assertEquals("Image Type", image.getImageSize(), blockDto.getImage().getImageSize());
    }

    private Image createImage() {
        Image image = new Image();
        image.setUrl("http://test/");
        image.setImageSize(ImageSize.MEDIUM);
        return image;
    }
}
