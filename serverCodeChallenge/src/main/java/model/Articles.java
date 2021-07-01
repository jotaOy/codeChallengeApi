package model;

public class Articles {
	private String title;
	private String description;
	private String content;
	private String url;
	private String image;
	private String publishedAt;
	private Source source;
	
	public Articles(String _title, String _description, String _content, String _url, String _image, String _publishedAt, Source _source) {
		this.title=_title;
		this.description=_description;
		this.content=_content;
		this.url=_url;
		this.image=_image;
		this.publishedAt=_publishedAt;
		this.source=_source;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}
	
}
