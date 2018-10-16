package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.db.Image;
import com.mhp.coding.challenges.mapping.models.db.blocks.*;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.models.dto.ImageDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    private ModelMapper modelMapper;

    public ArticleMapper() {
        this.modelMapper = new ModelMapper();
        initModelMappings();
    }

    void initModelMappings() {
        this.modelMapper.createTypeMap(Article.class, ArticleDto.class);
        this.modelMapper.createTypeMap(Image.class, ImageDto.class);

        // TODO: Report an issue on https://github.com/modelmapper/modelmapper/issues/new
        // The issue is related to the includes statements.
        // The mapping process ignores the children attributes of ArticleBlockDto.
        this.modelMapper.createTypeMap(ArticleBlock.class, ArticleBlockDto.class);
//                .include(ImageBlock.class, ImageBlockDto.class)
//                .include(GalleryBlock.class, GalleryBlockDto.class)
//                .include(TextBlock.class, TextBlockDto.class)
//                .include(VideoBlock.class, VideoBlockDto.class);

        // workaround to above mention issue
        this.modelMapper.createTypeMap(ImageBlock.class, ImageBlockDto.class);
        this.modelMapper.createTypeMap(GalleryBlock.class, GalleryBlockDto.class);
        this.modelMapper.createTypeMap(TextBlock.class, TextBlockDto.class);
        this.modelMapper.createTypeMap(VideoBlock.class, VideoBlockDto.class);

        // the modelMapper.map call is needed for the workaround
        // see https://github.com/modelmapper/modelmapper/issues/182#issuecomment-379166741
        modelMapper.typeMap(ImageBlock.class, ArticleBlockDto.class).setProvider(request ->
                modelMapper.map(request.getSource(), ImageBlockDto.class)
        );
        modelMapper.typeMap(GalleryBlock.class, ArticleBlockDto.class).setProvider(request ->
                modelMapper.map(request.getSource(), GalleryBlockDto.class)
        );
        modelMapper.typeMap(TextBlock.class, ArticleBlockDto.class).setProvider(request ->
                modelMapper.map(request.getSource(), TextBlockDto.class)
        );
        modelMapper.typeMap(VideoBlock.class, ArticleBlockDto.class).setProvider(request ->
                modelMapper.map(request.getSource(), VideoBlockDto.class)
        );

    }

    public ArticleDto map(Article article) {
        return modelMapper.map(article, ArticleDto.class);
    }

    public Article map(ArticleDto articleDto) {
        // Nicht Teil dieser Challenge.
        return new Article();
    }

}
