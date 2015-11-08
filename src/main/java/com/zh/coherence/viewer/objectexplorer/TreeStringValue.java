package com.zh.coherence.viewer.objectexplorer;

import org.jdesktop.swingx.renderer.StringValue;

import com.zh.coherence.viewer.objectexplorer.viewer.Viewer;

public class TreeStringValue implements StringValue {
    @Override
    public String getString(Object parent) {
        if (parent != null && parent instanceof Viewer) {
            return ((Viewer)parent).getNodeName();
        }else {
            return null;
        }
    }
}
