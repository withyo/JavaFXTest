package org.dimigo.gui.helloworld;

public class SearchType {
    private String text;   // 화면에 보여줄 텍스트
    private String value;  // 선택 시 가져올 값

    public SearchType(String text, String value) {
        this.text = text;
        this.value = value;
    }

    // getter, setter 생성
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return text;
    }
}
