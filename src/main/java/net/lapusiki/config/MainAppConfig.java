package net.lapusiki.config;

import info.debatty.java.stringsimilarity.Damerau;
import info.debatty.java.stringsimilarity.StringSimilarityInterface;
import net.lapusiki.core.QueryBuilder;
import net.lapusiki.core.service.VariableGenerator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.xml.ws.handler.MessageContext;

/**
 * Created by blvp on 16.05.15.
 */
@Configuration
@ComponentScan("net.lapusiki.core")
public class MainAppConfig {

    @Bean(name = "stringSimilarityDelegate")
    public StringSimilarityInterface damerau(){
        return new Damerau();
    }

}
