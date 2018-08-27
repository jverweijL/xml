<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">


<xsl:template match="/">
    <xsl:apply-templates />
</xsl:template>

    <xsl:template match="item">
        <xsl:variable name="itemnumber" select="count(preceding-sibling::item) + 1"/>
        <xsl:choose>
            <xsl:when test="$itemnumber mod 6 = 0">
                <div class="card">
                    <xsl:call-template name="cardbody"/>
                </div>
            </xsl:when>
            <xsl:when test="$itemnumber mod 6 = 1">
                2/3
            </xsl:when>
            <xsl:when test="$itemnumber mod 6 = 2">
                1/3
            </xsl:when>
            <xsl:when test="$itemnumber mod 6 = 3 or $itemnumber mod 6 = 4">
                1/2/1/2
            </xsl:when>
            <xsl:when test="$itemnumber mod 6 = 5">
                1/2
            </xsl:when>
        </xsl:choose>
        <!--<div class="card-type-asset form-check form-check-card form-check-top-left image-card">-->

        <!--</div>-->
    </xsl:template>

    <xsl:template name="cardbody">
        <div class="card-body">
            <div class="card-row">
                <div class="autofit-col autofit-col-expand">
                    <div class="card-title text-truncate" title="thumbnail_coffee.jpg">
                        <xsl:value-of select="./title"/>
                    </div>
                    <div class="card-subtitle text-truncate" title="Author Action">
                        <xsl:value-of select="./pubDate"/>
                    </div>
                    <div class="card-detail">
                        <xsl:value-of select="./description"/>
                    </div>
                </div>
            </div>
        </div>
    </xsl:template>

    <!-- override default template todo nothing -->
    <xsl:template match="text()"/>

</xsl:stylesheet>