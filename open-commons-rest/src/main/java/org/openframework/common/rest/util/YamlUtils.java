package org.openframework.common.rest.util;

import java.io.File;
import java.util.Properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.FileSystemResource;

public class YamlUtils {

    public static Properties loadYamlProperty(String fileName) {

        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        final File file = new File(fileName);
        yaml.setResources(new FileSystemResource(file));
        return yaml.getObject();
    }
}
