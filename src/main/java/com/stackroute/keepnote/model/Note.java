package com.stackroute.keepnote.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * The class "Note" will be acting as the data model for the note Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 */
@Entity
@Table(name="NOTE")
public class Note
{
	private int noteId;

	private String noteTitle;

	private String noteContent;

	private String noteStatus;

	private LocalDateTime createdAt;

	/**
	 * Empty Constructor
	 */
	public Note()
	{
		
	}
	
	public Note(final int noteId, final String noteTitle, final String noteContent, 
			final String noteStatus, final LocalDateTime createdAt)
	{
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.noteStatus = noteStatus;
		this.createdAt = createdAt;
	}

	/*@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQUENCE1")
	@SequenceGenerator(name="SEQUENCE1", sequenceName="note_seq", allocationSize=1)
	@Column(name = "note_id")*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getNoteId() {

		return noteId;
	}

	public String getNoteTitle() {

		return noteTitle;
	}

	public String getNoteContent() {

		return noteContent;
	}

	public String getNoteStatus() {

		return noteStatus;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = LocalDateTime.now();;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@Override
	public boolean equals(Object object)
	{
		if(this == object)
		{
			return  true;
		}
		if(object == null || getClass() != object.getClass()) {
			return false;
		}
		Note note = (Note)object;
		return noteId == note.noteId;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(noteId);
	}

	/* Override the toString() method */

	@Override
	public String toString() {
		return noteId +" " +noteTitle +" "+noteStatus+" "+ noteContent +" "+ createdAt;
	}
}