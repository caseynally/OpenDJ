/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at legal-notices/CDDLv1_0.txt
 * or http://forgerock.org/license/CDDLv1.0.html.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at legal-notices/CDDLv1_0.txt.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information:
 *      Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 *
 *      Copyright 2006-2008 Sun Microsystems, Inc.
 *      Portions Copyright 2015 ForgeRock AS
 */
package org.opends.server.monitors;

import org.opends.server.admin.std.server.MonitorProviderCfg;
import org.opends.server.api.MonitorProvider;
import org.opends.server.core.DirectoryServer;
import org.testng.annotations.Test;

/** This class defines a set of tests for the {@link BackendMonitor} class. */
@Test
public class BackendMonitorTestCase extends GenericMonitorTestCase
{
  /**
   * Creates a new instance of this test case class.
   *
   * @throws  Exception  If an unexpected problem occurred.
   */
  public BackendMonitorTestCase() throws Exception
  {
    super(null);
  }

  /**
   * Retrieves an initialized instance of the associated monitor provider.
   *
   * @return  An initialized instance of the associated monitor provider.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Override
  protected MonitorProvider getMonitorInstance()
         throws Exception
  {
    String monitorName = "userroot backend";
    MonitorProvider<? extends MonitorProviderCfg> provider =
         DirectoryServer.getMonitorProvider(monitorName);
    provider.initializeMonitorProvider(null);
    return provider;
  }
}
