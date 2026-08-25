package br.com.caroline.intibiome.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MinimalArticleModel {

    @ValueMapValue private String title;
    @ValueMapValue private String text;
    @ValueMapValue private String image;
    @ValueMapValue private String imageAlt;
    @ValueMapValue private String buttonText;
    @ValueMapValue @Default(values = "#") private String buttonLink;
    @ValueMapValue @Default(values = "right") private String imagePosition;
    @ValueMapValue @Default(values = "dark") private String theme;

    public String getTitle() { return title; }
    public String getText() { return text; }
    public String getImage() { return image; }
    public String getImageAlt() { return imageAlt; }
    public String getButtonText() { return buttonText; }
    public String getButtonLink() { return buttonLink; }
    public String getImagePosition() { return imagePosition; }
    public String getTheme() { return theme; }
}
