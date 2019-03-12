<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:variable name="viewcount"  select="5"/>

    <xsl:template match="/">
        <xsl:apply-templates />
    </xsl:template>

    <xsl:template match="item">
        <xsl:if test="count(preceding::item) &lt; $viewcount">
            <div class="card-type-asset form-check form-check-card form-check-top-left image-card">
                <div class="card">
                    <div class="card-body">
                        <div class="card-row">
                            <div class="autofit-col autofit-col-expand">
                                <div class="card-title">
                                    <xsl:value-of select="./title"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </xsl:if>
    </xsl:template>


    <!-- override default template todo nothing -->
    <xsl:template match="text()"/>

</xsl:stylesheet>