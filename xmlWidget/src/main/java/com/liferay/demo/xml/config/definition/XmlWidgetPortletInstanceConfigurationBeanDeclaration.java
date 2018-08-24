package com.liferay.demo.xml.config.definition;

import com.liferay.demo.xml.config.XmlWidgetConfiguration;
import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

public class XmlWidgetPortletInstanceConfigurationBeanDeclaration
        implements ConfigurationBeanDeclaration {
    @Override
    public Class<?> getConfigurationBeanClass() {
        return XmlWidgetConfiguration.class;
    }
}
