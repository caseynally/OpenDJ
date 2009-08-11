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
 *      Copyright 2009 Sun Microsystems, Inc.
 */

package org.opends.ldap.requests;



import org.opends.ldap.controls.Control;
import org.opends.server.types.ByteString;
import org.opends.types.ResultCode;



/**
 * A generic Bind request which should be used for unsupported
 * authentication methods. Servers that do not support a choice supplied
 * by a client return a Bind response with the result code set to
 * {@link ResultCode#AUTH_METHOD_NOT_SUPPORTED}.
 */
public interface GenericBindRequest extends BindRequest
{

  /**
   * {@inheritDoc}
   */
  GenericBindRequest addControl(Control control)
      throws UnsupportedOperationException, NullPointerException;



  /**
   * {@inheritDoc}
   */
  GenericBindRequest clearControls()
      throws UnsupportedOperationException;



  /**
   * {@inheritDoc}
   */
  Control getControl(String oid) throws NullPointerException;



  /**
   * {@inheritDoc}
   */
  Iterable<Control> getControls();



  /**
   * {@inheritDoc}
   */
  boolean hasControls();



  /**
   * {@inheritDoc}
   */
  Control removeControl(String oid)
      throws UnsupportedOperationException, NullPointerException;



  /**
   * {@inheritDoc}
   */
  String toString();



  /**
   * {@inheritDoc}
   */
  StringBuilder toString(StringBuilder builder)
      throws NullPointerException;



  /**
   * Returns the authentication information for this generic bind
   * request in a form defined by the authentication mechanism.
   *
   * @return The authentication information for this generic bind
   *         request in a form defined by the authentication mechanism.
   */
  ByteString getAuthenticationBytes();



  /**
   * Returns the authentication mechanism identifier for this generic
   * bind request. Note that value {@code 0} is reserved for simple
   * authentication, {@code 1} and {@code 2} are reserved but unused,
   * and {@code 3} is reserved for SASL authentication.
   *
   * @return The authentication mechanism identifier for this generic
   *         bind request.
   */
  byte getAuthenticationType();



  /**
   * Sets the authentication information for this generic bind request
   * in a form defined by the authentication mechanism.
   *
   * @param bytes
   *          The authentication information for this generic bind
   *          request in a form defined by the authentication mechanism.
   * @return This generic bind request.
   * @throws UnsupportedOperationException
   *           If this generic bind request does not permit the
   *           authentication bytes to be set.
   * @throws NullPointerException
   *           If {@code bytes} was {@code null}.
   */
  GenericBindRequest setAuthenticationBytes(ByteString bytes)
      throws UnsupportedOperationException, NullPointerException;



  /**
   * Sets the authentication mechanism identifier for this generic bind
   * request. Note that value {@code 0} is reserved for simple
   * authentication, {@code 1} and {@code 2} are reserved but unused,
   * and {@code 3} is reserved for SASL authentication.
   *
   * @param type
   *          The authentication mechanism identifier for this generic
   *          bind request.
   * @return This generic bind request.
   * @throws UnsupportedOperationException
   *           If this generic bind request does not permit the
   *           authentication type to be set.
   */
  GenericBindRequest setAuthenticationType(byte type)
      throws UnsupportedOperationException;
}
