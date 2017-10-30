/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.NoteService;
import dataaccess.NotesDBException;
import domainmodel.Note;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dan-romero-mancia
 */
public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NoteService service = new NoteService();
        String action = request.getParameter("action");
        
        if (action != null && action.equals("view")) {
            int noteId = Integer.parseInt(request.getParameter("selectedNote"));
            try {
                Note note = service.get(noteId);
                request.setAttribute("selectedNote", note);
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            ArrayList<Note> noteList = (ArrayList<Note>) service.getAll();
            request.setAttribute("noteTable", noteList);
            
        } catch (NotesDBException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        NoteService service = new NoteService();
        
        if (action.equals("add")) {
            String contents = request.getParameter("contents");
            if (contents != null && !contents.isEmpty()) {
                try {
                    service.insert(contents);
                    ArrayList<Note> noteList = (ArrayList<Note>) service.getAll();
                    request.setAttribute("noteTable", noteList);
                } catch (NotesDBException ex) {
                    Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
        }
        
        else if (action.equals("delete")) {
            String noteId = request.getParameter("selectedNote");
            try {
                service.delete(Integer.parseInt(noteId));
                ArrayList<Note> noteList = (ArrayList<Note>) service.getAll();
                request.setAttribute("noteTable", noteList);
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if (action.equals("edit")) {
            String contents = request.getParameter("contents");
            int noteId = Integer.parseInt(request.getParameter("noteId"));
            try {
                service.update(noteId, contents);
                ArrayList<Note> noteList = (ArrayList<Note>) service.getAll();
                request.setAttribute("noteTable", noteList);
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

}
