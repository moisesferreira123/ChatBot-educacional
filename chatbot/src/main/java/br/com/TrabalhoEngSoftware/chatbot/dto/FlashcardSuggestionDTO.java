package br.com.TrabalhoEngSoftware.chatbot.dto;

public class FlashcardSuggestionDTO {
    private String front;
    private String back;

    public FlashcardSuggestionDTO() {
    }

    public FlashcardSuggestionDTO(String front, String back) {
        this.front = front;
        this.back = back;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }
}