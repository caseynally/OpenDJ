<!--
  ! CDDL HEADER START
  !
  ! The contents of this file are subject to the terms of the
  ! Common Development and Distribution License, Version 1.0 only
  ! (the "License").  You may not use this file except in compliance
  ! with the License.
  !
  ! You can obtain a copy of the license at legal-notices/CDDLv1_0.txt
  ! or http://forgerock.org/license/CDDLv1.0.html.
  ! See the License for the specific language governing permissions
  ! and limitations under the License.
  !
  ! When distributing Covered Code, include this CDDL HEADER in each
  ! file and include the License file at legal-notices/CDDLv1_0.txt.
  ! If applicable, add the following below this CDDL HEADER, with the
  ! fields enclosed by brackets "[]" replaced with your own identifying
  ! information:
  !      Portions Copyright [yyyy] [name of copyright owner]
  !
  ! CDDL HEADER END
  !
  !
  !      Copyright 2008 Sun Microsystems, Inc.
  ! -->
<xsl:stylesheet version="1.0" xmlns:adm="http://www.opends.org/admin"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <!-- 
    Templates for processing duration properties.
  -->
  <xsl:template match="adm:duration" mode="java-value-type">
    <xsl:value-of select="'Long'" />
  </xsl:template>
  <xsl:template match="adm:duration" mode="java-value-primitive-type">
    <xsl:value-of select="'long'" />
  </xsl:template>
  <xsl:template match="adm:duration" mode="java-definition-type">
    <xsl:value-of select="'DurationPropertyDefinition'" />
  </xsl:template>
  <xsl:template match="adm:duration" mode="java-definition-ctor">
    <xsl:if test="boolean(@allow-unlimited)">
      <xsl:value-of
        select="concat('      builder.setAllowUnlimited(',
                       @allow-unlimited, ');&#xa;')" />
    </xsl:if>
    <xsl:if test="boolean(@base-unit)">
      <xsl:value-of
        select="concat('      builder.setBaseUnit(&quot;',
                       @base-unit, '&quot;);&#xa;')" />
    </xsl:if>
    <xsl:if test="boolean(@maximum-unit)">
      <xsl:value-of
        select="concat('      builder.setMaximumUnit(&quot;',
                       @maximum-unit, '&quot;);&#xa;')" />
    </xsl:if>
    <xsl:if test="boolean(@upper-limit)">
      <xsl:value-of
        select="concat('      builder.setUpperLimit(&quot;',
                       @upper-limit, '&quot;);&#xa;')" />
    </xsl:if>
    <xsl:if test="boolean(@lower-limit)">
      <xsl:value-of
        select="concat('      builder.setLowerLimit(&quot;',
                       @lower-limit, '&quot;);&#xa;')" />
    </xsl:if>
  </xsl:template>
</xsl:stylesheet>
