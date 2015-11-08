package com.zh.coherence.viewer.tools.query.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;

import com.zh.coherence.viewer.tools.query.QueryTool;
import com.zh.coherence.viewer.utils.icons.IconLoader;

/**
 * Created by IntelliJ IDEA.
 * User: Живко
 * Date: 26.05.12
 * Time: 15:57
 */
public class SaveCohQlAction extends AbstractAction {
    private QueryTool tool;

    public SaveCohQlAction(QueryTool tool) {
        this.tool = tool;

        putValue(Action.SMALL_ICON, new IconLoader("icons/save.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        int ret = chooser.showSaveDialog((Component) e.getSource());
        if(ret == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            if(!file.exists()){
                file.getParentFile().mkdirs();
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(tool.getEditor().getText().getBytes());
                    fos.flush();
                    fos.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
