
<%@ include file="./init.jsp" %>



<%
    XmlWidgetConfiguration xmlWidgetConfiguration =
            (XmlWidgetConfiguration)
                    renderRequest.getAttribute(XmlWidgetConfiguration.class.getName());

    String xmlSource = StringPool.BLANK;
    String xslSource = StringPool.BLANK;

    if (Validator.isNotNull(xmlWidgetConfiguration)) {
        xmlSource =
                portletPreferences.getValue(
                        "xmlSource", xmlWidgetConfiguration.xmlSource());
        xslSource =
                portletPreferences.getValue(
                        "xslSource", xmlWidgetConfiguration.xslSource());
    }
%>

<%--<liferay-portlet:actionURL portletConfiguration="<%= true %>"
                           var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>"
                           var="configurationRenderURL" />


<liferay-frontend:edit-form
        action="<%= configurationActionURL %>"
        method="post"
        name="fm">

    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
    <aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

    <liferay-frontend:edit-form-body>
        <liferay-frontend:fieldset-group>
            <liferay-frontend:fieldset
                    collapsed="<%= false %>"
                    collapsible="<%= true %>"
                    label="xmlsource3">

            <aui:field-wrapper label="xmlsource3">

                <div><aui:input cssClass="lfr-input-text-container" label="xml-url-name" name="xmlsource" type="text" value="<%= xmlSource%>" /></div>
                <div><aui:input cssClass="lfr-input-text-container" label="xsl-url-name" name="xslsource" type="text" value="<%= xslSource%>" /></div>

            </aui:field-wrapper>

            </liferay-frontend:fieldset>
        </liferay-frontend:fieldset-group>
        </liferay-frontend:edit-form-body>
    <liferay-frontend:edit-form-footer>
        <aui:button type="submit" />
        <aui:button type="cancel" />
    </liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>--%>

<liferay-portlet:actionURL portletConfiguration="<%=true%>"
                           var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%=true%>"
                           var="configurationRenderURL" />

<aui:form action="<%=configurationActionURL%>" method="post" name="fm">
    <aui:input name="<%=Constants.CMD%>" type="hidden"
               value="<%=Constants.UPDATE%>" />

    <aui:input name="redirect" type="hidden"
               value="<%=configurationRenderURL%>" />

    <aui:fieldset>
        <div><aui:input cssClass="lfr-input-text-container" label="xml-url-name" name="xmlSource" type="text" value="<%= xmlSource%>" /></div>
        <div><aui:input cssClass="lfr-input-text-container" label="xsl-url-name" name="xslSource" type="text" value="<%= xslSource%>" /></div>
    </aui:fieldset>



    <aui:button-row>
        <aui:button type="submit"></aui:button>
    </aui:button-row>
</aui:form>