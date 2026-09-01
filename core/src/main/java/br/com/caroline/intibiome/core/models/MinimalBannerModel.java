package br.com.caroline.intibiome.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MinimalBannerModel {

    @ValueMapValue private String image;
    @ValueMapValue private String imageAlt;
    @ValueMapValue private String title;
    @ValueMapValue @Default(values = "h2") private String headingLevel;
    @ValueMapValue @Default(values = "center") private String alignment;
    @ValueMapValue @Default(values = "light") private String textColor;
    @ValueMapValue @Default(values = "medium") private String overlay;
    @ValueMapValue @Default(values = "standard") private String height;
    @ValueMapValue @Default(values = "center-center") private String focalPoint;

    public String getImage() { return image; }
    public String getImageAlt() { return imageAlt; }
    public String getTitle() { return title; }
    public String getHeadingLevel() { return "h1".equals(headingLevel) ? "h1" : "h2"; }
    public String getAlignment() { return alignment; }
    public String getTextColor() { return textColor; }
    public String getOverlay() { return overlay; }
    public String getHeight() { return height; }
    public String getFocalPoint() { return focalPoint; }
}
