package br.com.TrabalhoEngSoftware.chatbot.dto;

public class NoteUpdateDTO {
	private String title;
  private String content;
  private String subtitle;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
}
