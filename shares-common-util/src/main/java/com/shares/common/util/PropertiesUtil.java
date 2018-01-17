package com.shares.common.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/2/7
 * @description
 */
public class PropertiesUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

    private PropertiesUtil() {
        throw new IllegalArgumentException("not instance for you");
    }

    public static Properties load(ClassPathResource file) {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(file.getFile());
            prop.load(in);
        } catch (Exception e) {
            LOGGER.error("{}", e);
        } finally {
            IOUtils.closeQuietly(in);
        }
        return prop;
    }
}
