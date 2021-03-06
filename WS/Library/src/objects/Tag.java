package objects;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;;

@Entity
public class Tag {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "article_tag", joinColumns = { @JoinColumn(name = "Tag_id") }, inverseJoinColumns = {
			@JoinColumn(name = "Article_id") })
	private Set<Article> artigos;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
