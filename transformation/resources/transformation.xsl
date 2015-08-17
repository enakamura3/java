<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">

	<xsl:template match="/">
		<root>
			<xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
			<output>
				<listaCount>
					<xsl:value-of select="count(//*[contains(local-name(),'lista')])" />
				</listaCount>
				<itemCount>
					<xsl:value-of select="count(//*[contains(local-name(),'item')])" />
				</itemCount>
			</output>
			<xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
		</root>
	</xsl:template>
</xsl:stylesheet>
