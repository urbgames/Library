package view;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import model.PersistenceDAO;
import objects.Annotation;
import objects.Article;
import objects.CriteriaSearch;
import objects.Tag;

@Path("")
@Provider
public class View {

	PersistenceDAO persistenceDAO = new PersistenceDAO();

	@GET
	@Path("/teste")
	@Produces("application/json; charset=UTF-8")
	public String getClassificacao() {
		return "Ol�";
	}

	@POST
	@Path("/createTag")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tag createTag(Tag tag) {
		persistenceDAO.createTag(tag);
		return tag;
	}

	@POST
	@Path("/createArticle")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Article createArticle(Article article) {
		persistenceDAO.createArticle(article);
		return article;
	}

	@GET
	@Path("/createTag")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tag findTagByName(Tag tag) {
		persistenceDAO.findTagByName(tag.getName());
		return tag;
	}

	@POST
	@Path("/updateTag")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tag updateTag(Tag tag) {
		persistenceDAO.updateTag(tag);
		return tag;
	}

	@POST
	@Path("/removeTag")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tag removeTag(Tag tag) {
		persistenceDAO.removeTag(tag);
		return tag;
	}

	@GET
	@Path("/getAllTags")
	@Produces(MediaType.APPLICATION_JSON)
	public List getAllTags() {
		return persistenceDAO.getAllTags();
	}

	@GET
	@Path("/getAllArticles")
	@Produces(MediaType.APPLICATION_JSON)
	public List getAllArticles() {
		return persistenceDAO.getAllArticles();
	}

	@POST
	@Path("/updateArticle")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Article updateArticle(Article article) {
		persistenceDAO.updateArticle(article);
		return article;
	}

	@POST
	@Path("/removeArticle")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Article removeArticle(Article article) {
		persistenceDAO.removeArticle(article);
		return article;
	}

	@POST
	@Path("/createAnnotation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Annotation createAnnotation(Annotation annotation) {
		persistenceDAO.createAnnotation(annotation);
		return annotation;
	}

	@POST
	@Path("/removeAnnotations")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Annotation removeAnnotations(Annotation annotation) {
		persistenceDAO.removeAnnotations(annotation);
		return annotation;
	}

	@POST
	@Path("/getArticlesByCriteria")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List getArticlesByCriteria(CriteriaSearch criteriaSearch) {
		return persistenceDAO.getArticlesByCriteria(criteriaSearch);
	}

	@POST
	@Path("/getArticleById")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Article getArticleById(Integer id) {
		return persistenceDAO.getArticleById(id);
	}

	@POST
	@Path("/getAnnotationsByTag")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List getAnnotationsByTag(CriteriaSearch criteriaSearch) {
		if (criteriaSearch.getTags().size() > 0)
			return persistenceDAO.getAnnotationsByTag(criteriaSearch);
		else
			return persistenceDAO.getAllAnnotations();
	}

	@POST
	@Path("/updateAnnotation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Annotation updateAnnotation(Annotation annotation) {
		persistenceDAO.updateAnnotation(annotation);
		return annotation;
	}

}
