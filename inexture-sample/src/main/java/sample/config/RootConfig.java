/*
 * 
 */
package sample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * The Class RootConfig.
 */
@Configuration
@Import(DataConfig.class)
@ComponentScan("sample")
public class RootConfig {

}
