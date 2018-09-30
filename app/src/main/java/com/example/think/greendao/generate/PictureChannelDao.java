package com.example.think.greendao.generate;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.think.greendao.entity.PictureChannel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PICTURE_CHANNEL".
*/
public class PictureChannelDao extends AbstractDao<PictureChannel, Long> {

    public static final String TABLENAME = "PICTURE_CHANNEL";

    /**
     * Properties of entity PictureChannel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ChannelId = new Property(1, String.class, "channelId", false, "CHANNEL_ID");
        public final static Property ChannelName = new Property(2, String.class, "channelName", false, "CHANNEL_NAME");
    }


    public PictureChannelDao(DaoConfig config) {
        super(config);
    }
    
    public PictureChannelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PICTURE_CHANNEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CHANNEL_ID\" TEXT NOT NULL UNIQUE ," + // 1: channelId
                "\"CHANNEL_NAME\" TEXT NOT NULL UNIQUE );"); // 2: channelName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PICTURE_CHANNEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PictureChannel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getChannelId());
        stmt.bindString(3, entity.getChannelName());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PictureChannel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getChannelId());
        stmt.bindString(3, entity.getChannelName());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PictureChannel readEntity(Cursor cursor, int offset) {
        PictureChannel entity = new PictureChannel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // channelId
            cursor.getString(offset + 2) // channelName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PictureChannel entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setChannelId(cursor.getString(offset + 1));
        entity.setChannelName(cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PictureChannel entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PictureChannel entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PictureChannel entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
