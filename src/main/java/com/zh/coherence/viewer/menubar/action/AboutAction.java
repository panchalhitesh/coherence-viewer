package com.zh.coherence.viewer.menubar.action;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;

import org.jdesktop.swingx.JXImageView;

import com.zh.coherence.viewer.utils.icons.IconLoader;
import com.zh.coherence.viewer.utils.ui.ZHDialog;

/**
 * Created by IntelliJ IDEA.
 * User: Живко
 * Date: 26.05.12
 * Time: 17:28
 */
public class AboutAction extends AbstractAction{

    public AboutAction() {
        putValue(Action.NAME, "About");
        putValue(Action.SMALL_ICON, new IconLoader("icons/information.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JXImageView view = new JXImageView();
        try {
            view.setImage(new File("config/splash.png"));
            view.setDragEnabled(false);
            view.setEditable(false);
            ZHDialog dialog = new ZHDialog(view, "About");
            dialog.setModal(true);
            dialog.show(410,300);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
