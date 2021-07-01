package model;

public class Source {
	
	private String name;
	private String url;
	
	public Source(String _name, String _url) {
		this.name=_name;
		this.url=_url;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
