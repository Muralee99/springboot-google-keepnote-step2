package com.stackroute.keepnote.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.model.Note;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus 
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */
@Repository
@Transactional
public class NoteDAOImpl implements NoteDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */
	@Autowired
	private SessionFactory sessionFactory = null;

	private Session session = null;

	public NoteDAOImpl(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		//sessionFactory.getCurrentSession().createQuery("delete from NOTE").executeUpdate();

	}

	/*
	 * Save the note in the database(note) table.
	 */

	public boolean saveNote(final Note note)
	{
		boolean isSuccess = true;
		session = sessionFactory.openSession();
		Transaction transaction = null;

		try
		{
			transaction = session.beginTransaction();
			session.save(note);
			transaction.commit();
		}
		catch (HibernateException exception)
		{
			isSuccess = false;
			if (transaction != null)
			{
				transaction.rollback();
			}
		}
		finally
		{
			session.close();
		}

		return isSuccess;
	}

	/*
	 * Remove the note from the database(note) table.
	 */

	public boolean deleteNote(final int noteId)
	{
		boolean isSuccess = true;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Note note = session.get(Note.class, noteId);
			session.delete(note);
			transaction.commit();
		}
		catch (HibernateException exception)
		{
			isSuccess = false;
			if (transaction!=null) {
				transaction.rollback();
			}
			//e.printStackTrace();
		} finally {
			session.close();
		}

		return isSuccess;

	}

	/*
	 * retrieve all existing notes sorted by created Date in descending
	 * order(showing latest note first)
	 */
	public List<Note> getAllNotes()
	{
		session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Note> notes = null;

		try
		{
			transaction = session.beginTransaction();
			notes = session.createQuery("From Note",Note.class).list();
			transaction.commit();
		}
		catch (HibernateException exception)
		{
			if (transaction != null)
			{
				transaction.rollback();
			}
		}
		finally
		{
			session.close();
		}

		return  notes;
	}

	/*
	 * retrieve specific note from the database(note) table
	 */
	public Note getNoteById(final int noteId)
	{
		session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Note> note = null;

		try
		{
			transaction = session.beginTransaction();
			note = session.createQuery("From Note where noteId = "+noteId, Note.class).list();
			transaction.commit();
		}
		catch (HibernateException exception)
		{
			if (transaction != null)
			{
				transaction.rollback();
			}
		}
		finally
		{
			session.close();
		}
		return note.get(0);

	}

	/* Update existing note */

	public boolean UpdateNote(final Note note)
	{
		boolean isSuccess = true;
		session = sessionFactory.openSession();
		Transaction transaction = null;

		try
		{
			transaction = session.beginTransaction();
			session.save(note);
			transaction.commit();
		}
		catch (HibernateException exception)
		{
			isSuccess = false;
			if (transaction != null) transaction.rollback();
		}
		finally
		{
			session.close();
		}

		return isSuccess;		
	}

}
