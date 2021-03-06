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
 *      Copyright 2008-2009 Sun Microsystems, Inc.
 *      Portions copyright 2013 ForgeRock AS.
 */
package org.forgerock.opendj.server.core;

/**
 * An object that registers to be notified of events generated by a
 * {@link DataProviderConnection} object. Data provider events may be triggered
 * as a result of:
 * <ul>
 * <li>The data provider connection being closed.
 * <li>An operational error. For example, a proxy data provider might lose
 * connectivity with the remote server.
 * <li>A configuration change. For example, a data provider may be disabled, or
 * have a new base DN added.
 * <li>An administrative action. For example, a data provider may be temporarily
 * disabled during an import or restore from backup.
 * </ul>
 * In the case of configuration changes, the data provider will only notify its
 * listeners once the configuration change has been applied. If a listener
 * wishes to validate configuration changes before they are applied to a data
 * provider then the listener should use the registration methods provided by
 * {@code DataProviderCfg}.
 */
public interface DataProviderEventListener {

    /**
     * The data provider has changed state due to an operational error,
     * configuration change, or an administrative action.
     * <p>
     * Implementations should examine the provided {@link DataProviderEvent} in
     * order to determine how the data provider has changed.
     *
     * @param event
     *            The data provider event.
     */
    void handleDataProviderEvent(DataProviderEvent event);

}
