<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:strip-space elements="*"/>	

	<xsl:output method="html" encoding="UTF-8" />

<xsl:template match="donnee">
<html>
<head>
<title> test </title>
</head>
<body><xsl:apply-templates/><br/><br/></body>
</html>
	</xsl:template>
	<!--xsl:template match="defibrilateur">
	  &lt;quartier nom="<xsl:value-of select="@quartier"/>"&gt;<br/>
	    &lt;sous-quartier nom="<xsl:value-of select="@sousquartier"/>"&gt;<br/>
	      &lt;defibrilateur lieu="<xsl:value-of select="@Lieu"/>" adresse="<xsl:value-of select="@Adresse"/>"  x="<xsl:value-of select="@x"/>"  y="<xsl:value-of select="@y"/>"&gt;&lt;/defibrilateur&gt;<br/>
	    &lt;/sous-quartier&gt;<br/>
	  &lt;/quartier&gt;<br/>
	</xsl:template-->
	
	<xsl:template match="/">
	
	&lt;?xml version="1.0"?&gt; <br/>
&lt;rdf:RDF  <br/>
    xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"   <br/>
    xmlns:coord="http://spatial.ucd.ie/lod/osn/page/term/k:KSJ2:"   <br/>
    xmlns:xsd="http://www.w3.org/2001/XMLSchema#"   <br/>
    xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"   <br/>
    xmlns:osm="http://spatial.ucd.ie/lod/osn/page/term/k:amenity/v:"&gt;<br/>
		<xsl:apply-templates select="//*" />
		
		&lt;/rdf:RDF&gt;  <br/>
			</xsl:template>

		

	<xsl:template match="node">
	
		
	<xsl:for-each select=".">
	
	&lt;rdf:Description rdf:about="http://wiki.openstreetmap.org/wiki/Node"&gt;<br/>
	  &lt;coord:lat&gt;<xsl:value-of select="@lat"/>&lt;/coord:lat&gt;<br/>
	   &lt;coord:long&gt;<xsl:value-of select="@lon"/>&lt;/coord:long&gt;<br/>
	   <xsl:call-template name="tags">
		</xsl:call-template> 
		</xsl:for-each>
		&lt;/rdf:Description&gt;<br/>
	</xsl:template>
	
	
	
	<xsl:template name="tags">
	<xsl:for-each select="./tag">
	<xsl:if test= "@k = 'amenity'">
	   &lt;osm:amenity&gt;<xsl:value-of select="@v"/>&lt;/osm:amenity&gt;<br/>
	</xsl:if>
	
	<xsl:if test= "@k = 'name'">
	   &lt;osm:name&gt;<xsl:value-of select="@v"/>&lt;/osm:name&gt;<br/>
	</xsl:if>
	 
	  
	  </xsl:for-each>
	</xsl:template>
	
	<!--<xsl:template match="@sousquartier">

	<xsl:variable name="ssquartiers" select="@sousquartier" />
	&lt;sousquartier nom="<xsl:value-of select="$ssquartiers"/>"&gt;<br/>
	 &lt;/sousquartier&gt;<br/>	
	 
	 </xsl:template>-->



</xsl:stylesheet>


<!--<xsl:for-each select="/*">
	<xsl:if test="quariter courant = quartier '">
			
			</xsl:if>
		</xsl:for-each> -->