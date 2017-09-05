package objects;

import java.util.Set;

public class CriteriaSearch {

	private Set<String> tags;
	private String name, authors, journalOrEvent;
	private String logicalAuthors, logicalYear, logicalJournalOrEvent, logicalTags;
	private int yearStart, yearEnd;
	
	public Set<String> getTags() {
		return tags;
	}

	public String getName() {
		return name;
	}

	public String getAuthors() {
		return authors;
	}

	public String getJournalOrEvent() {
		return journalOrEvent;
	}

	public String getLogicalAuthors() {
		return logicalAuthors;
	}

	public String getLogicalYear() {
		return logicalYear;
	}

	public String getLogicalJournalOrEvent() {
		return logicalJournalOrEvent;
	}

	public String getLogicalTags() {
		return logicalTags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public void setJournalOrEvent(String journalOrEvent) {
		this.journalOrEvent = journalOrEvent;
	}

	public void setLogicalAuthors(String logicalAuthors) {
		this.logicalAuthors = logicalAuthors;
	}

	public void setLogicalYear(String logicalYear) {
		this.logicalYear = logicalYear;
	}

	public void setLogicalJournalOrEvent(String logicalJournalOrEvent) {
		this.logicalJournalOrEvent = logicalJournalOrEvent;
	}

	public void setLogicalTags(String logicalTags) {
		this.logicalTags = logicalTags;
	}

	public int getYearStart() {
		return yearStart;
	}

	public void setYearStart(int yearStart) {
		this.yearStart = yearStart;
	}

	public int getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(int yearEnd) {
		this.yearEnd = yearEnd;
	}

	
}
