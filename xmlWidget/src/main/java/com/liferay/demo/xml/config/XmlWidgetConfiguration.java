package com.liferay.demo.xml.config;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;


@ExtendedObjectClassDefinition(
        category = "Dynamic Content",
        scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
        id = "com.liferay.demo.xml.config.XmlWidgetConfiguration",
        localization = "content/Language",
        name = "xmlwidget-configuration-name"
)
public interface XmlWidgetConfiguration {

    @Meta.AD(
            deflt = "https://s3.eu-central-1.amazonaws.com/lfry/example.xml",
            description = "xml-url-description",
            name = "xml-url-name",
            required = false
    )
    public String xmlSource();

    @Meta.AD(
            deflt = "https://s3.eu-central-1.amazonaws.com/lfry/example.xsl",
            description = "xsl-url-description",
            name = "xsl-url-name",
            required = false
    )
    public String xslSource();

    @Meta.AD(
            deflt = "false",
            description = "xsl-cache-description",
            name = "xsl-cache-name",
            required = false
    )
    public boolean cacheXSL();

}
