package org.openframework.commons.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = { "org.openframework.commons.config.service" })
public class GlobalConfigSpringRootContextConfig {

}
