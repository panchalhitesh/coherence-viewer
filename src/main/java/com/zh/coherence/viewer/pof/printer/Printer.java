package com.zh.coherence.viewer.pof.printer;

import java.util.Formatter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.zh.coherence.viewer.pof.ValueContainer;

@XmlRootElement(name = "printer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Printer {

    @XmlElement(name = "pof-id")
    private int pofId;

    @XmlElement(name = "format")
    private String format;

    public String print(ValueContainer container){
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format(format, container.getValues().toArray());

        return sb.toString();
    }

    public int getPofId() {
        return pofId;
    }

    public void setPofId(int pofId) {
        this.pofId = pofId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
