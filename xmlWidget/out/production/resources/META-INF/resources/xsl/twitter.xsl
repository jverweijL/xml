<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:variable name="viewcount"  select="3"/>

    <xsl:template match="/">
        <xsl:text>jan was here</xsl:text>
        <xsl:apply-templates />
    </xsl:template>



    <!-- override default template todo nothing -->
    <xsl:template match="text()"/>

</xsl:stylesheet>