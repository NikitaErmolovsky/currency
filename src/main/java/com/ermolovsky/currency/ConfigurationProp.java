package com.ermolovsky.currency;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Validated
@ConfigurationProperties(prefix = "application.properties")
public class ConfigurationProp {
    @NotEmpty(message = "fill your app id")
    @Getter @Setter
    private String myAppId;
    @NotEmpty(message = "fill your gif id")
    @Getter @Setter
    private String myGifId;
    @Pattern( regexp = "[A-Z]{3}", message = "wrong currency format")
    @Getter @Setter
    private String currency;

}
