package com.zh.coherence.viewer.console;

import javax.swing.*;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by IntelliJ IDEA.
 * User: Живко
 * Date: 13.02.12
 * Time: 21:24
 */
public class JTextAreaWriter extends Writer{
    private boolean closed = false;
    private JTextArea textArea;
    private StringBuffer buffer = new StringBuffer();

    /** Constructor.

     @param textArea The JTextArea to write to.
     */

    public JTextAreaWriter(JTextArea textArea){
        setTextArea(textArea);
    }

    /** Set the JTextArea to write to.

     @param textArea The JTextArea
     */

    public void setTextArea(JTextArea textArea){
        if(textArea == null){
            throw new IllegalArgumentException("The text area must not be null.");
        }
        this.textArea = textArea;
    }

    /** Close the stream. */

    public void close(){
        closed = true;
    }

    /** Flush the data that is currently in the buffer.

     @throws IOException
     */

    public void flush() throws IOException{
        if(closed){
            throw new IOException("The stream is not open.");
        }
        // the newline character should not be necessary.  The PrintWriter
        // should autmatically put the newline, but it doesn't seem to work
        textArea.append(getBuffer().toString());
        if(System.getProperty("java.version").startsWith("1.1")){
            textArea.append("\n");
        }
        textArea.setCaretPosition(textArea.getDocument().getLength());
        buffer = new StringBuffer();
    }

    /** Write the given character array to the output stream.

     @param charArray The character array
     @throws IOException
     */

    public void write(char[] charArray) throws IOException{
        write(charArray, 0, charArray.length);
    }

    /** Write the given character array to the output stream beginning from
     the given offset and proceeding to until the given length is reached.

     @param charArray The character array
     @param offset The start offset
     @param length The length to write
     @throws IOException
     */

    public void write(char[] charArray, int offset, int length) throws IOException{
        if(closed){
            throw new IOException("The stream is not open.");
        }
        getBuffer().append(charArray, offset, length);
    }

    /** Write the given character to the output stream.

     @param c The character
     @throws IOException
     */

    public void write(int c) throws IOException{
        if(closed){
            throw new IOException("The stream is not open.");
        }
        getBuffer().append((char)c);
    }

    /** Write the given String to the output stream.

     @param string The String
     @throws IOException
     */

    public void write(String string) throws IOException{
        if(closed){
            throw new IOException("The stream is not open.");
        }
        getBuffer().append(string);
    }

    /** Write the given String to the output stream beginning from the given offset
     and proceeding to until the given length is reached.

     @param string The String
     @param offset The start offset
     @param length The length to write
     @throws IOException
     */

    public void write(String string, int offset, int length) throws IOException{
        if(closed){
            throw new IOException("The stream is not open.");
        }
        getBuffer().append(string.substring(offset, length));
    }

    public StringBuffer getBuffer() {
        return buffer;
    }
}
