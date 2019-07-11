package org.dimigo.gui.helloworld;

import javafx.beans.property.SimpleStringProperty;

public class lateSearch {
    @Override
    public String toString() {
        return "lateSearch{" +
                "sickCd=" + sickCd +
                ", sickNm=" + sickNm +
                '}';
    }
    public lateSearch(String sickCd, String sickNm) {
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

    private SimpleStringProperty sickCd;
    private SimpleStringProperty sickNm;

}
