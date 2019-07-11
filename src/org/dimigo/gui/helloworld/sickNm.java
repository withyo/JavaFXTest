package org.dimigo.gui.helloworld;

import javafx.beans.property.SimpleStringProperty;

public class sickNm {


        private SimpleStringProperty sickCd;
        private SimpleStringProperty sickNm;

    public sickNm(String sickNm,String sickCd ) {
        this.sickCd = new SimpleStringProperty(sickCd);
        this.sickNm = new SimpleStringProperty(sickNm);
    }
    public String getSickCd() {
        return sickCd.get();
    }

    public SimpleStringProperty sickCdProperty() {
        return sickCd;
    }

    public void setSickCd(String sickCd) {
        this.sickCd.set(sickCd);
    }

    public String getSickNm() {
        return sickNm.get();
    }

    public SimpleStringProperty sickNmProperty() {
        return sickNm;
    }

    public void setSickNm(String sickNm) {
        this.sickNm.set(sickNm);
    }

    @Override
    public String toString() {
        return "sickNm{" +
                "sickNm=" + sickNm +
                ", sickCd=" + sickCd +
                '}';
    }
}

