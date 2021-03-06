package objects;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

@Entity
public class Article {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(length = 2014)
	private String citation;
	
	private String name, authors, localStorage, journalOrEvent;
	private int year;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinTable(name = "article_tag", joinColumns = { @JoinColumn(name = "Article_id") }, inverseJoinColumns = {
			@JoinColumn(name = "Tag_id") })
	private Set<Tag> tags;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "article", cascade = CascadeType.REMOVE)
	private Set<Annotation> annotations;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getCitation() {
		return citation;
	}

	public void setCitation(String citation) {
		this.citation = citation;
	}

	public String getLocalStorage() {
		return localStorage;
	}

	public void setLocalStorage(String localStorage) {
		this.localStorage = localStorage;
	}
	
	public String getJournalOrEvent() {
		return journalOrEvent;
	}

	public void setJournalOrEvent(String journalOrEvent) {
		this.journalOrEvent = journalOrEvent;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(Set<Annotation> annotations) {
		this.annotations = annotations;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
