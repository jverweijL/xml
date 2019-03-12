package com.liferay.demo.xml.portlet;

import com.liferay.demo.xml.config.XmlWidgetConfiguration;
import com.liferay.demo.xml.constants.xmlWidgetPortletKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import net.sf.saxon.s9api.*;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * @author jverweij
 */
@Component(
	configurationPid = "com.liferay.demo.xml.config.XmlWidgetConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.custom",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + xmlWidgetPortletKeys.xmlWidget,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class xmlWidgetPortlet extends MVCPortlet {

	String xmlSource = "https://s3.eu-central-1.amazonaws.com/lfry/example.xml";
	String xslSource = "https://s3.eu-central-1.amazonaws.com/lfry/example.xsl";
	ByteArrayOutputStream result;


	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		//https://github.com/snac-cooperative/saxon-9-he-samples/blob/master/ext_simple.java

		//renderRequest.setAttribute(XmlWidgetConfiguration.class.getName(),_configuration);

		// fetch xml
		// fetch xsl and compile
		// TODO cache compiled xsl
		// transform
		// TODO cache result
		try
		{
			xmlSource = renderRequest.getPreferences().getValue("xmlSource", "");
			if (xmlSource.isEmpty() && !_configuration.xmlSource().isEmpty()) {
				System.out.println("Using xmlSource from system config");
				xmlSource = _configuration.xmlSource();
			}
			System.out.println("Using xmlSource: " + xmlSource);

			xslSource = renderRequest.getPreferences().getValue("xslSource", "");
			if (xslSource.isEmpty() && !_configuration.xslSource().isEmpty()) {
				System.out.println("Using xslSource from system config");
				xslSource = _configuration.xslSource();
			}
			System.out.println("Using xslSource: " + xslSource);

			Processor proc = new Processor(false);
			//proc.registerExtensionFunction(sqrt);
			XsltCompiler comp = proc.newXsltCompiler();
			//System.out.println(getSource(xslSource));
			// TODO add method to fetch url in more robust way with httpclient of some sort
			XsltExecutable exp = comp.compile(new StreamSource(new URL(xslSource).openStream()));
			XsltTransformer trans = exp.load();
			// TODO add method to fetch url in more robust way with httpclient of some sort
			//System.out.println(getSource(xmlSource));
			XdmNode source = proc.newDocumentBuilder().build(new StreamSource(new URL(xmlSource).openStream()));
			trans.setInitialContextNode(source);
			Serializer out = proc.newSerializer();
			out.setOutputProperty(Serializer.Property.METHOD, "html");
			result = new ByteArrayOutputStream();
			out.setOutputStream(result);
			trans.setDestination(out);
			trans.transform();
		}
		catch (SaxonApiException | FileNotFoundException ex)
		{
			ex.printStackTrace();
			// throw sae;
			result = new ByteArrayOutputStream();
			result.write("Exception: ".getBytes());
			result.write(ex.getMessage().getBytes());
		}


		renderRequest.setAttribute("result", result.toString());
		super.doView(renderRequest, renderResponse);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(XmlWidgetConfiguration.class, properties);
	}

	private volatile XmlWidgetConfiguration _configuration;

}
