package com.zh.coherence.viewer.tools.report.jmx.quadratic;

import java.util.Collection;

import javax.swing.JPanel;

public interface QuadraticLabel extends QuadraticTagHandler {

    public void lightFireflies(Collection<Firefly> fireflies);

    public JPanel getLabelPanel();
}
