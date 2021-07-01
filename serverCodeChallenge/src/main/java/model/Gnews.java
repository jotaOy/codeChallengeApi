package model;

import java.util.List;

public class Gnews {
	private int totalArticles;
	private List<Articles> articles;
	private List<Object> errors;
	
	public Gnews(int _totalArticles, List<Articles> _articles) {
		this.articles=_articles;
		this.totalArticles=_totalArticles;
	}
	
	public Gnews() {
	}
	public int getTotalArticles() {
		return totalArticles;
	}
	public void setTotalArticles(int totalArticles) {
		this.totalArticles = totalArticles;
	}
	public List<Articles> getArticles() {
		return articles;
	}
	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}
	public List<Object> getErrors() {
		return errors;
	}
	public void setErrors(List<Object> errors) {
		this.errors = errors;
	}
}
