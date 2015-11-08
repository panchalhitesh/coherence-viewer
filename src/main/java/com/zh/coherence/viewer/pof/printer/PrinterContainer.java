package com.zh.coherence.viewer.pof.printer;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "printer-container")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrinterContainer {

    @XmlElement
    private Collection<Printer> printers;

    public Collection<Printer> getPrinters() {
        return printers;
    }

    public void setPrinters(Collection<Printer> printers) {
        this.printers = printers;
    }
}
