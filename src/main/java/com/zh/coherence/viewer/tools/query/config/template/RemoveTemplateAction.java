package com.zh.coherence.viewer.tools.query.config.template;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.jdesktop.swingx.JXList;

import com.zh.coherence.viewer.utils.icons.IconLoader;

public class RemoveTemplateAction extends AbstractAction {

    private TemplateListModel listModel;
    private JXList list;

    public RemoveTemplateAction(TemplateListModel listModel, JXList list) {
        this.listModel = listModel;
        this.list = list;

        putValue(Action.SMALL_ICON, new IconLoader("icons/minus.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object selected = list.getSelectedValue();
        if(selected != null){
            RSyntaxTextArea.getCodeTemplateManager().removeTemplate(((TemplateListModel.CodeTemplateWrapper) selected).ct);
            listModel.reload();
        }

    }
}
