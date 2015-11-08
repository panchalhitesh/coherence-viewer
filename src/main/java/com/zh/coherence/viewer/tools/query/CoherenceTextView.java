package com.zh.coherence.viewer.tools.query;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Field;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.tangosol.coherence.dslquery.CoherenceQuery;
import com.tangosol.coherence.dslquery.QueryPlus;
import com.zh.coherence.viewer.console.JTextAreaWriter;

public class CoherenceTextView extends JPanel {
    private JTextArea textArea = new JTextArea();
    private PrintWriter printWriter;
    private QueryPlus plus;

    public CoherenceTextView() {
        super(new BorderLayout());
        Writer writer = new JTextAreaWriter(textArea);
        printWriter = new PrintWriter(writer);

        add(new JScrollPane(textArea), BorderLayout.CENTER);

        plus = new QueryPlus();
        textArea.setFont(new Font("Dialog", Font.PLAIN, 14));
    }

    public void printObject(Object result, QueryEngine queryEngine){
        CoherenceQuery query = (CoherenceQuery) queryEngine.getBackend();
        try {
            Field field = plus.getClass().getDeclaredField("m_lastQuery");
            field.setAccessible(true);
            field.set(plus, query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        textArea.setText("");
        plus.printResults(printWriter, result);
        printWriter.flush();
    }
}
