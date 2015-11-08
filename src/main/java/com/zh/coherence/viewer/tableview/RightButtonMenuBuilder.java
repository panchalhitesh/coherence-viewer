package com.zh.coherence.viewer.tableview;

import java.util.List;

import javax.swing.JPopupMenu;

import com.zh.coherence.viewer.ApplicationPane;
import com.zh.coherence.viewer.tableview.user.UserObjectViewer;
import com.zh.coherence.viewer.tableview.user.UserViewerAction;

public class RightButtonMenuBuilder {
    private ApplicationPane applicationPane;

    public JPopupMenu buildMenu(Object value, List<UserObjectViewer> viewers){
        JPopupMenu menu = new JPopupMenu();

        for (UserObjectViewer viewer : viewers){
            if(viewer.isSupport(value)){
                menu.add(new UserViewerAction(viewer, value));
            }
        }
        return menu;
    }

    public ApplicationPane getApplicationPane() {
        return applicationPane;
    }

    public void setApplicationPane(ApplicationPane applicationPane) {
        this.applicationPane = applicationPane;
    }
}
