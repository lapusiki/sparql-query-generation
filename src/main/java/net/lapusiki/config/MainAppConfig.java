package net.lapusiki.config;

import info.debatty.java.stringsimilarity.Damerau;
import info.debatty.java.stringsimilarity.StringSimilarityInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by blvp on 16.05.15.
 */
@Configuration
@ComponentScan("net.lapusiki.core")
public class MainAppConfig {

    @Bean
    public StringSimilarityInterface damerau(){
        return new Damerau();
    }

}
