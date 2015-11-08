package com.zh.coherence.viewer.menubar;

import javax.swing.JMenu;

import com.zh.coherence.viewer.menubar.action.AboutAction;
import com.zh.coherence.viewer.menubar.action.UserClassViewerAction;

/**
 * Created by IntelliJ IDEA.
 * User: Живко
 * Date: 26.05.12
 * Time: 17:22
 */
public class HelpJMenuBuilder {
    public JMenu buildMenu() {
        JMenu menu = new JMenu("Help");

        menu.add(new AboutAction());
        menu.addSeparator();
        menu.add(new UserClassViewerAction());

        return menu;
    }
}
