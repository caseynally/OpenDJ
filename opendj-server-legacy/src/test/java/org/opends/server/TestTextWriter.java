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
 *      Portions Copyright 2015 ForgeRock AS.
 */
package org.opends.server;

import org.opends.server.loggers.TextWriter;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class TestTextWriter implements TextWriter
{
  /** The list that will hold the messages logged. */
  private final LinkedList<String> messageList;

  public TestTextWriter()
  {
    messageList = new LinkedList<>();
  }

  public synchronized void writeRecord(String record)
  {
    messageList.add(record);
  }

  /** {@inheritDoc} */
  public void flush()
  {
    // No implementation is required.
  }

  /** {@inheritDoc} */
  public void shutdown()
  {
    messageList.clear();
  }

  /** {@inheritDoc} */
  public long getBytesWritten()
  {
    // No implementation is required. Just return 0;
    return 0;
  }

    /**
   * Retrieves a copy of the set of messages logged to this error logger since
   * the last time it was cleared.  A copy of the list is returned to avoid
   * a ConcurrentModificationException.
   *
   * @return  The set of messages logged to this error logger since the last
   *          time it was cleared.
   */
  public synchronized List<String> getMessages()
  {
    return new ArrayList<>(messageList);
  }

  /** Clears any messages currently stored by this logger. */
  public synchronized void clear()
  {
    messageList.clear();
  }
}
