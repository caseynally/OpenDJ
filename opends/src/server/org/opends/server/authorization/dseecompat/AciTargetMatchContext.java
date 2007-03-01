/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at
 * trunk/opends/resource/legal-notices/OpenDS.LICENSE
 * or https://OpenDS.dev.java.net/OpenDS.LICENSE.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at
 * trunk/opends/resource/legal-notices/OpenDS.LICENSE.  If applicable,
 * add the following below this CDDL HEADER, with the fields enclosed
 * by brackets "[]" replaced with your own identifying information:
 *      Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 *
 *      Portions Copyright 2007 Sun Microsystems, Inc.
 */

package org.opends.server.authorization.dseecompat;

import org.opends.server.types.AttributeType;
import org.opends.server.types.AttributeValue;
import org.opends.server.types.Entry;
import java.util.LinkedList;

/**
 * The AciTargetMatchContext interface provides a
 * view of an AciContainer that exposes information to be
 * used by the Aci.isApplicable() method to determine if
 * an ACI is applicable (targets matched) to the LDAP operation,
 * operation rights and entry and attributes having access
 * checked on.
 */
public interface AciTargetMatchContext {
    /**
     * Set the deny ACI list.
     * @param denyList The deny ACI list.
     */
    public void setDenyList(LinkedList<Aci> denyList);

    /**
     * Set the allow ACI list.
     * @param allowList The list of allow ACIs.
     */
    public void setAllowList(LinkedList<Aci> allowList);

    /**
     * Get the entry being evaluated. This is known as the
     * resource entry.
     * @return The entry being evaluated.
     */
    public Entry getResourceEntry();

    /**
     * Get the current attribute type being evaluated.
     * @return  The attribute type being evaluated.
     */
    public AttributeType getCurrentAttributeType();

    /**
     * The current attribute type value being evaluated.
     * @return The current attribute type value being evaluated.
     */
    public AttributeValue getCurrentAttributeValue();

    /**
     * True if the first attribute of the resource entry is being evaluated.
     * @return True if this is the first attribute.
     */
    public boolean isFirstAttribute();

    /**
     * Set to true if the first attribute of the resource entry is
     * being evaluated.
     * @param isFirst  True if this is the first attribute of the
     * resource entry being evaluated.
     */
    public void setIsFirstAttribute(boolean isFirst);

    /**
     * Set the attribute type to be evaluated.
     * @param type  The attribute type to set to.
     */
    public void setCurrentAttributeType(AttributeType type);

    /**
     * Set the attribute value to be evaluated.
     * @param v The current attribute value to set to.
     */
    public void setCurrentAttributeValue(AttributeValue v);

    /**
     * True if the target matching code found an entry test rule. An
     * entry test rule is an ACI without a targetattr target rule.
     * @param val True if an entry test rule was found.
     */
    public void setEntryTestRule(boolean val);

    /**
     * True if an entry test rule was found.
     * @return True if an entry test rule was found.
     */
    public boolean hasEntryTestRule();

    /**
     * Return the rights for this container's LDAP operation.
     * @return  The rights for the container's LDAP operation.
     */
    public int getRights();

    /**
     * Checks if the container's rights has the specified rights.
     * @param  rights The rights to check for.
     * @return True if the container's rights has the specified rights.
     */
    public boolean hasRights(int rights);

    /**
     * Set the rights of the container to the specified rights.
     * @param rights The rights to set the container's rights to.
     */
    public void setRights(int rights);
}


