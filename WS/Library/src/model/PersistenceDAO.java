package model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Query;
import org.hibernate.Session;

import objects.Annotation;
import objects.Article;
import objects.CriteriaSearch;
import objects.Tag;

public class PersistenceDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("library");
	EntityManager manager = factory.createEntityManager();

	public void createTag(Tag tag) {
		manager.getTransaction().begin();
		manager.persist(tag);
		manager.getTransaction().commit();
	}

	public List findTagByName(String name) {
		Session session = manager.unwrap(Session.class);
		String hql = "from article where name like :name";
		Query query = session.createQuery(hql);
		query.setString("name", "%" + name + "%");
		session.close();
		return query.list();
	}

	public List getAllTags() {
		Session session = manager.unwrap(Session.class);
		List tags = session.createCriteria(Tag.class).list();
		session.clear();
		session.close();
		return tags;
	}

	public Tag getTagById(Integer id) {
		Session session = manager.unwrap(Session.class);
		Tag returno = (Tag) session.get(Tag.class, id);
		session.close();
		return returno;
	}

	public void updateTag(Tag tag) {
		Session session = manager.unwrap(Session.class);
		session.beginTransaction();
		session.update(tag);
		session.getTransaction().commit();
		session.close();
	}

	public void removeTag(Tag tag) {
		manager.getTransaction().begin();
		manager.remove(manager.merge(tag));
		manager.getTransaction().commit();
	}

	public void createArticle(Article article) {
		manager.getTransaction().begin();
		manager.persist(article);
		manager.getTransaction().commit();
	}

	public List getAllArticles() {
		Session session = manager.unwrap(Session.class);
		List articles = session.createQuery("from Article").list();
		session.close();
		return articles;
	}

	public List getAnnotationsByTag(CriteriaSearch criteriaSearch) {
		String range = criteriaSearch.getTags().toString().replace('[', '(').replace(']', ')');
		String queryBase = "select article from Annotation annotation inner join annotation.article article where annotation.id in  (select annotation.id from Annotation annotation INNER JOIN annotation.tags tags where tags.id in "
				+ range + " )";
		Session session = manager.unwrap(Session.class);
		Query query = session.createQuery(queryBase);
		List<Article> annotations = query.list();
		session.close();
		return annotations;
	}

	public List getArticlesByCriteria(CriteriaSearch criteriaSearch) {

		String queryBase = "select distinct article from Article article LEFT JOIN fetch article.tags tags";
		String queryTemp = "";

		if (criteriaSearch.getName() != "")
			queryTemp += " article.name like '%" + criteriaSearch.getName() + "%' ";
		if (criteriaSearch.getAuthors() != "") {
			if (queryTemp != "")
				queryTemp += " " + criteriaSearch.getLogicalAuthors() + " ";
			queryTemp += " article.authors like '%" + criteriaSearch.getAuthors() + "%' ";
		}
		if (criteriaSearch.getJournalOrEvent() != "") {
			if (queryTemp != "")
				queryTemp += " " + criteriaSearch.getLogicalJournalOrEvent() + " ";
			queryTemp += " article.journalOrEvent like '%" + criteriaSearch.getJournalOrEvent() + "%' ";
		}
		if (criteriaSearch.getYearStart() != 0 && criteriaSearch.getYearEnd() != 0) {
			if (queryTemp != "")
				queryTemp += " " + criteriaSearch.getLogicalYear() + " ";
			queryTemp += " article.year between " + criteriaSearch.getYearStart() + " and "
					+ criteriaSearch.getYearEnd() + " ";
		} else if (criteriaSearch.getYearStart() != 0) {
			if (queryTemp != "")
				queryTemp += " " + criteriaSearch.getLogicalYear() + " ";
			queryTemp += " article.year >= " + criteriaSearch.getYearStart() + " ";
		} else if (criteriaSearch.getYearEnd() != 0) {
			if (queryTemp != "")
				queryTemp += " " + criteriaSearch.getLogicalYear() + " ";
			queryTemp += " article.year <= " + criteriaSearch.getYearEnd() + " ";
		}

		if (criteriaSearch.getTags().size() != 0) {
			String range = criteriaSearch.getTags().toString().replace('[', '(').replace(']', ')');
			if (queryTemp != "")
				queryTemp += " " + criteriaSearch.getLogicalTags() + " ";
			queryTemp += " tags.id in " + range + " group by article having count(distinct tags.id)="
					+ criteriaSearch.getTags().size();
		}
		if (queryTemp != "")
			queryTemp = " where " + queryTemp;
		queryBase += queryTemp;
		Session session = manager.unwrap(Session.class);
		Query query = session.createQuery(queryBase);
		List articles = query.list();
		session.close();
		return articles;
	}

	public void updateArticle(Article article) {
		Session session = manager.unwrap(Session.class);
		session.beginTransaction();
		session.update(article);
		session.getTransaction().commit();
		session.close();
	}

	public Article getArticleById(Integer id) {
		Session session = manager.unwrap(Session.class);
		Article returno = (Article) session.get(Article.class, id);
		session.close();
		return returno;
	}

	public void removeArticle(Article article) {
		manager.getTransaction().begin();
		manager.remove(manager.merge(article));
		manager.getTransaction().commit();
	}

	public void createAnnotation(Annotation annotation) {
		manager.getTransaction().begin();
		manager.persist(annotation);
		manager.getTransaction().commit();
	}

	public void updateAnnotation(Annotation annotation) {
		Session session = manager.unwrap(Session.class);
		session.beginTransaction();
		String hqlUpdate = "update Annotation annotation set annotation.annotations = :annotations where annotation.id = :id";
		session.createQuery(hqlUpdate).setString("annotations", annotation.getAnnotations())
				.setInteger("id", annotation.getId()).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	public void removeAnnotations(Annotation annotation) {
		manager.getTransaction().begin();
		manager.remove(manager.merge(annotation));
		manager.getTransaction().commit();
	}

	public List getAllAnnotations() {
		String queryBase = "select distinct article from Annotation annotation inner join annotation.article article where annotation.id in (select annotation.id from Annotation annotation)";
		Session session = manager.unwrap(Session.class);
		Query query = session.createQuery(queryBase);
		List<Article> annotations = query.list();
		session.close();
		return annotations;
	}
	
}
