<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="1.0"
    xmlns:visual="http://aaa.com/visual">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>gems.xsl</title>
            </head>
            <body>
                <table>
                    <tr>
                        <td>id</td>
                        <td>name</td>
                        <td>preciousness</td>
                        <td>origin</td>
                        <td>color</td>
                        <td>opacity</td>
                        <td>verges</td>
                        <td>value</td>
                    </tr>
                    <xsl:for-each select="gems/gem">
                        <tr>
                            <td><xsl:value-of select="@id"/></td>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="preciousness"/></td>
                            <td><xsl:value-of select="origin"/></td>
                            <td><xsl:value-of select="parameters/visual:color"/></td>
                            <td><xsl:value-of select="parameters/visual:opacity"/></td>
                            <td><xsl:value-of select="parameters/visual:verges"/></td>
                            <td><xsl:value-of select="value"/></td>
                        </tr>
                        
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>