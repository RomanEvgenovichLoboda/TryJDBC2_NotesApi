package com.roman.jdbch2.repository;

import com.roman.jdbch2.model.NoteModel;
import com.roman.jdbch2.service.DbNoteService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NoteRepository {
    private DbNoteService dbService;

    public NoteRepository() {
        try {
            dbService = new DbNoteService();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Iterable<NoteModel> selectAll() throws SQLException {
        String sel = "SELECT * FROM notes";
        ResultSet set = dbService.getSelectQuery(sel);
        List<NoteModel> list = new ArrayList<>();
        while (set.next()) {
            list.add(new NoteModel(set.getLong("id"), set.getString("author"), set.getString("text"), set.getTimestamp("datetime")));
        }
        return list;
    }

    public NoteModel selectById(long id) throws SQLException {
        String sel = "SELECT * FROM notes WHERE id = " + id;
        ResultSet set = dbService.getSelectQuery(sel);
        if (set.next()) {
            NoteModel note = new NoteModel(set.getLong("id"), set.getString("author"), set.getString("text"), set.getTimestamp("datetime"));
            return note;
        }
        return null;
    }

    public String deleteById(long id) {
        String del = "DELETE FROM notes WHERE id =" + id;
        return dbService.execute(del);
    }

    public String updateById(long id, String text) {
        String upd = "UPDATE notes SET text = '" + text + "' WHERE id = " + id;
        return dbService.execute(upd);
    }

    public String addNew(NoteModel model) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String insert = "INSERT INTO notes(author,text,datetime) VALUES ('" + model.getAuthor() + "','" + model.getText() + "','" + time + "')";
        return dbService.execute(insert);
    }
}
