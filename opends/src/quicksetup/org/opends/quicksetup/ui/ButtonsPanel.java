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
 *      Portions Copyright 2006-2007 Sun Microsystems, Inc.
 */

package org.opends.quicksetup.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashSet;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.opends.quicksetup.ButtonName;
import org.opends.quicksetup.Step;
import org.opends.quicksetup.event.ButtonActionListener;
import org.opends.quicksetup.event.ButtonEvent;
import org.opends.quicksetup.util.Utils;

/**
 * This class contains the buttons in the bottom of the Install/Uninstall
 * dialog.  There is only one of this instances for the QuickSetupDialog.
 * The layout is updated calling setCurrentStep method.
 *
 */
class ButtonsPanel extends QuickSetupPanel
{
  private static final long serialVersionUID = -8460400337486357976L;

  private HashSet<ButtonActionListener> buttonListeners =
      new HashSet<ButtonActionListener>();

  private JButton nextButton;

  private JButton previousButton;

  private JButton quitButton;

  private JButton closeButton;

  private JButton finishButton;

  private JButton cancelButton;

  /**
   * Default constructor.
   *
   */
  public ButtonsPanel()
  {
    createButtons();
    layoutButtons();
  }

  /**
   * Adds a button listener.  All the button listeners will be notified when
   * the buttons are clicked (by the user or programatically).
   * @param l the ButtonActionListener to be added.
   */
  public void addButtonActionListener(ButtonActionListener l)
  {
    buttonListeners.add(l);
  }

  /**
   * Removes a button listener.
   * @param l the ButtonActionListener to be removed.
   */
  public void removeButtonActionListener(ButtonActionListener l)
  {
    buttonListeners.remove(l);
  }

  /**
   * Updates the layout of the panel so that it corresponds to the Step passed
   * as parameter.
   *
   * @param step the step in the wizard.
   */
  public void setDisplayedStep(Step step)
  {
    switch (step)
    {
    case WELCOME:

      previousButton.setVisible(false);
      nextButton.setVisible(true);
      finishButton.setVisible(false);
      quitButton.setVisible(true);
      closeButton.setVisible(false);
      cancelButton.setVisible(false);

      break;

    case REVIEW:

      previousButton.setVisible(true);
      nextButton.setVisible(false);
      finishButton.setVisible(true);
      quitButton.setVisible(true);
      closeButton.setVisible(false);
      cancelButton.setVisible(false);

      break;

    case PROGRESS:

      // TO COMPLETE: if there is an error we might want to change
      // this
      // like for instance coming back
      previousButton.setVisible(false);
      nextButton.setVisible(false);
      finishButton.setVisible(false);
      quitButton.setVisible(false);
      closeButton.setVisible(true);
      cancelButton.setVisible(false);

      break;

    case CONFIRM_UNINSTALL:

      previousButton.setVisible(false);
      nextButton.setVisible(false);
      finishButton.setVisible(true);
      quitButton.setVisible(false);
      closeButton.setVisible(false);
      cancelButton.setVisible(true);

      break;

    default:

      previousButton.setVisible(true);
      nextButton.setVisible(true);
      finishButton.setVisible(false);
      quitButton.setVisible(true);
      closeButton.setVisible(false);
      cancelButton.setVisible(false);
    }
  }

  /**
   * Returns the button corresponding to the buttonName.
   * @param buttonName the ButtonName for which we want to get the button.
   * @return the button corresponding to the buttonName.
   */
  public JButton getButton(ButtonName buttonName)
  {
    JButton b = null;
    switch (buttonName)
    {
    case NEXT:
      b = nextButton;
      break;

    case PREVIOUS:
      b = previousButton;
      break;

    case QUIT:
      b = quitButton;
      break;

    case CLOSE:
      b = closeButton;
      break;

    case FINISH:
      b = finishButton;
      break;

    case CANCEL:
      b = cancelButton;
      break;

    default:
      throw new IllegalArgumentException("Unknown button name: " +
          buttonName);
    }

    return b;
  }

  /*
   * Create the buttons.
   */
  private void createButtons()
  {
    nextButton =
        createButton("next-button-label", "next-button-tooltip",
            ButtonName.NEXT);

    previousButton =
        createButton("previous-button-label", "previous-button-tooltip",
            ButtonName.PREVIOUS);

    String tooltip;

    tooltip = "quit-button-install-tooltip";
    quitButton =
        createButton("quit-button-label", tooltip, ButtonName.QUIT);

    tooltip = Utils.isUninstall()?
        "close-button-uninstall-tooltip":"close-button-install-tooltip";
    closeButton = createButton("close-button-label", tooltip, ButtonName.CLOSE);

    String label = Utils.isUninstall()?
        "finish-button-uninstall-label":"finish-button-install-label";
    tooltip = Utils.isUninstall()?
        "finish-button-uninstall-tooltip":"finish-button-install-tooltip";
    finishButton = createButton(label, tooltip, ButtonName.FINISH);

    cancelButton =
      createButton("cancel-button-label", "cancel-button-uninstall-tooltip",
          ButtonName.CANCEL);
  }

  /**
   * Do the layout of the panel.
   *
   */
  private void layoutButtons()
  {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    GridBagConstraints gbcAux = new GridBagConstraints();
    gbcAux.gridwidth = GridBagConstraints.REMAINDER;
    gbcAux.fill = GridBagConstraints.HORIZONTAL;
    JPanel previousPanel = new JPanel(new GridBagLayout());
    // Set as opaque to inherit the background color of ButtonsPanel
    previousPanel.setOpaque(false);
    previousPanel.add(previousButton, gbcAux);
    int width = (int) previousButton.getPreferredSize().getWidth();
    previousPanel.add(Box.createHorizontalStrut(width), gbcAux);

    gbc.gridwidth = 5;
    gbc.weightx = 0.0;
    gbc.weighty = 0.0;
    gbc.insets.bottom = 0;
    gbc.insets.right = UIFactory.HORIZONTAL_INSET_BETWEEN_BUTTONS;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.NONE;
    add(previousPanel, gbc);
    gbc.gridwidth--;

    JPanel nextFinishPanel = new JPanel(new GridBagLayout());
    // Set as opaque to inherit the background color of ButtonsPanel
    nextFinishPanel.setOpaque(false);
    nextFinishPanel.add(nextButton, gbcAux);
    if (!Utils.isUninstall())
    {
      nextFinishPanel.add(finishButton, gbcAux);
    }
    width =
        (int) Math.max(nextButton.getPreferredSize().getWidth(), finishButton
            .getPreferredSize().getWidth());
    nextFinishPanel.add(Box.createHorizontalStrut(width), gbcAux);
    add(nextFinishPanel, gbc);

    gbc.gridwidth--;
    gbc.weightx = 1.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets.right = 0;
    add(Box.createHorizontalGlue(), gbc);

    gbc.gridwidth = GridBagConstraints.RELATIVE;
    gbc.weightx = 0.0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.insets.left = UIFactory.HORIZONTAL_INSET_BETWEEN_BUTTONS;
    if (Utils.isUninstall())
    {
      gbc.insets.right = UIFactory.HORIZONTAL_INSET_BETWEEN_BUTTONS;
      add(finishButton, gbc);
      gbc.insets.right = 0;
    }

    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.weightx = 0.0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.insets.left = 0;
    JPanel quitCloseCancelPanel = new JPanel(new GridBagLayout());
    // Set as opaque to inherit the background color of ButtonsPanel
    quitCloseCancelPanel.setOpaque(false);
    quitCloseCancelPanel.add(
        Box.createHorizontalStrut(UIFactory.HORIZONTAL_INSET_BETWEEN_BUTTONS),
        gbcAux);
    quitCloseCancelPanel.add(quitButton, gbcAux);
    quitCloseCancelPanel.add(closeButton, gbcAux);
    quitCloseCancelPanel.add(cancelButton, gbcAux);
    width =
        (int) Math.max(quitButton.getPreferredSize().getWidth(), closeButton
            .getPreferredSize().getWidth());
    width = (int) Math.max(width, cancelButton.getPreferredSize().getWidth());
    quitCloseCancelPanel.add(Box.createHorizontalStrut(width), gbcAux);
    add(quitCloseCancelPanel, gbc);
  }

  /**
   * Create a button.
   * @param labelKey the key in the properties file for the label.
   * @param tooltipKey the key in the properties file for the tooltip.
   * @param buttonName the ButtonName.
   * @return a new button with the specified parameters.
   */
  private JButton createButton(String labelKey, String tooltipKey,
      ButtonName buttonName)
  {
    JButton b = UIFactory.makeJButton(getMsg(labelKey), getMsg(tooltipKey));

    final ButtonName fButtonName = buttonName;

    ActionListener actionListener = new ActionListener()
    {
      public void actionPerformed(ActionEvent ev)
      {
        ButtonEvent be = new ButtonEvent(ev.getSource(), fButtonName);
        for (ButtonActionListener li : buttonListeners)
        {
          li.buttonActionPerformed(be);
        }
      }
    };

    b.addActionListener(actionListener);

    return b;
  }
}
