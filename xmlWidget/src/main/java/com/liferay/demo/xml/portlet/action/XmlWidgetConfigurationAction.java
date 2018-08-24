package com.liferay.demo.xml.portlet.action;


import com.liferay.demo.xml.config.XmlWidgetConfiguration;
import com.liferay.demo.xml.constants.xmlWidgetPortletKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.*;
import org.osgi.service.component.annotations.*;

import javax.portlet.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component(
        configurationPid = "com.liferay.demo.xml.portlet.action.XmlWidgetConfiguration",
        configurationPolicy = ConfigurationPolicy.OPTIONAL,
        immediate = true,
        property = {"javax.portlet.name=" + xmlWidgetPortletKeys.xmlWidget},
        service = ConfigurationAction.class
)
public class XmlWidgetConfigurationAction extends DefaultConfigurationAction {
    @Override
    public void processAction(
            PortletConfig portletConfig, ActionRequest actionRequest,
            ActionResponse actionResponse)
            throws Exception {

        //TODO looping over request params would perhaps work better?

        String xmlSource = ParamUtil.getString(actionRequest, "xmlSource");
        String xslSource = ParamUtil.getString(actionRequest, "xslSource");

        setPreference(actionRequest, "xmlSource", xmlSource);
        setPreference(actionRequest, "xslSource", xslSource);

        super.processAction(portletConfig, actionRequest, actionResponse);
    }

    @Override
    public void include(
            PortletConfig portletConfig, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {

        httpServletRequest.setAttribute(
                XmlWidgetConfiguration.class.getName(),
                _configuration);

        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _configuration = ConfigurableUtil.createConfigurable(
                XmlWidgetConfiguration.class, properties);

    }

    private volatile XmlWidgetConfiguration _configuration;
}
