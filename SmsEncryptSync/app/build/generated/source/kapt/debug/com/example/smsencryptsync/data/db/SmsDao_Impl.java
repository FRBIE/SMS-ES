package com.example.smsencryptsync.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.smsencryptsync.data.model.DecryptedSms;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SmsDao_Impl implements SmsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DecryptedSms> __insertionAdapterOfDecryptedSms;

  private final EntityDeletionOrUpdateAdapter<DecryptedSms> __updateAdapterOfDecryptedSms;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSmsUploadStatus;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSms;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllSms;

  public SmsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDecryptedSms = new EntityInsertionAdapter<DecryptedSms>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `decrypted_sms` (`id`,`sender`,`body`,`timestamp`,`serverMsgId`,`isSyncedUp`,`isSyncedDown`,`deviceId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DecryptedSms entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getSender() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getSender());
        }
        if (entity.getBody() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBody());
        }
        statement.bindLong(4, entity.getTimestamp());
        if (entity.getServerMsgId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getServerMsgId());
        }
        final int _tmp = entity.isSyncedUp() ? 1 : 0;
        statement.bindLong(6, _tmp);
        final int _tmp_1 = entity.isSyncedDown() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        if (entity.getDeviceId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getDeviceId());
        }
      }
    };
    this.__updateAdapterOfDecryptedSms = new EntityDeletionOrUpdateAdapter<DecryptedSms>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `decrypted_sms` SET `id` = ?,`sender` = ?,`body` = ?,`timestamp` = ?,`serverMsgId` = ?,`isSyncedUp` = ?,`isSyncedDown` = ?,`deviceId` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DecryptedSms entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getSender() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getSender());
        }
        if (entity.getBody() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBody());
        }
        statement.bindLong(4, entity.getTimestamp());
        if (entity.getServerMsgId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getServerMsgId());
        }
        final int _tmp = entity.isSyncedUp() ? 1 : 0;
        statement.bindLong(6, _tmp);
        final int _tmp_1 = entity.isSyncedDown() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        if (entity.getDeviceId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getDeviceId());
        }
        statement.bindLong(9, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateSmsUploadStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE decrypted_sms SET isSyncedUp = 1, serverMsgId = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteSms = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM decrypted_sms WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllSms = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM decrypted_sms";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final DecryptedSms sms, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfDecryptedSms.insertAndReturnId(sms);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<DecryptedSms> smsList,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDecryptedSms.insert(smsList);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final DecryptedSms sms, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfDecryptedSms.handle(sms);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateSmsUploadStatus(final long id, final String serverMsgId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSmsUploadStatus.acquire();
        int _argIndex = 1;
        if (serverMsgId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, serverMsgId);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateSmsUploadStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSms(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSms.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteSms.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllSms(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllSms.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllSms.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<DecryptedSms>> getAllSms() {
    final String _sql = "SELECT * FROM decrypted_sms ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"decrypted_sms"}, false, new Callable<List<DecryptedSms>>() {
      @Override
      @Nullable
      public List<DecryptedSms> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
          final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfServerMsgId = CursorUtil.getColumnIndexOrThrow(_cursor, "serverMsgId");
          final int _cursorIndexOfIsSyncedUp = CursorUtil.getColumnIndexOrThrow(_cursor, "isSyncedUp");
          final int _cursorIndexOfIsSyncedDown = CursorUtil.getColumnIndexOrThrow(_cursor, "isSyncedDown");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
          final List<DecryptedSms> _result = new ArrayList<DecryptedSms>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DecryptedSms _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSender;
            if (_cursor.isNull(_cursorIndexOfSender)) {
              _tmpSender = null;
            } else {
              _tmpSender = _cursor.getString(_cursorIndexOfSender);
            }
            final String _tmpBody;
            if (_cursor.isNull(_cursorIndexOfBody)) {
              _tmpBody = null;
            } else {
              _tmpBody = _cursor.getString(_cursorIndexOfBody);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpServerMsgId;
            if (_cursor.isNull(_cursorIndexOfServerMsgId)) {
              _tmpServerMsgId = null;
            } else {
              _tmpServerMsgId = _cursor.getString(_cursorIndexOfServerMsgId);
            }
            final boolean _tmpIsSyncedUp;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSyncedUp);
            _tmpIsSyncedUp = _tmp != 0;
            final boolean _tmpIsSyncedDown;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsSyncedDown);
            _tmpIsSyncedDown = _tmp_1 != 0;
            final String _tmpDeviceId;
            if (_cursor.isNull(_cursorIndexOfDeviceId)) {
              _tmpDeviceId = null;
            } else {
              _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
            }
            _item = new DecryptedSms(_tmpId,_tmpSender,_tmpBody,_tmpTimestamp,_tmpServerMsgId,_tmpIsSyncedUp,_tmpIsSyncedDown,_tmpDeviceId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getSmsById(final long id, final Continuation<? super DecryptedSms> $completion) {
    final String _sql = "SELECT * FROM decrypted_sms WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DecryptedSms>() {
      @Override
      @Nullable
      public DecryptedSms call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
          final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfServerMsgId = CursorUtil.getColumnIndexOrThrow(_cursor, "serverMsgId");
          final int _cursorIndexOfIsSyncedUp = CursorUtil.getColumnIndexOrThrow(_cursor, "isSyncedUp");
          final int _cursorIndexOfIsSyncedDown = CursorUtil.getColumnIndexOrThrow(_cursor, "isSyncedDown");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
          final DecryptedSms _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSender;
            if (_cursor.isNull(_cursorIndexOfSender)) {
              _tmpSender = null;
            } else {
              _tmpSender = _cursor.getString(_cursorIndexOfSender);
            }
            final String _tmpBody;
            if (_cursor.isNull(_cursorIndexOfBody)) {
              _tmpBody = null;
            } else {
              _tmpBody = _cursor.getString(_cursorIndexOfBody);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpServerMsgId;
            if (_cursor.isNull(_cursorIndexOfServerMsgId)) {
              _tmpServerMsgId = null;
            } else {
              _tmpServerMsgId = _cursor.getString(_cursorIndexOfServerMsgId);
            }
            final boolean _tmpIsSyncedUp;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSyncedUp);
            _tmpIsSyncedUp = _tmp != 0;
            final boolean _tmpIsSyncedDown;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsSyncedDown);
            _tmpIsSyncedDown = _tmp_1 != 0;
            final String _tmpDeviceId;
            if (_cursor.isNull(_cursorIndexOfDeviceId)) {
              _tmpDeviceId = null;
            } else {
              _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
            }
            _result = new DecryptedSms(_tmpId,_tmpSender,_tmpBody,_tmpTimestamp,_tmpServerMsgId,_tmpIsSyncedUp,_tmpIsSyncedDown,_tmpDeviceId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getSmsByServerId(final String serverMsgId,
      final Continuation<? super DecryptedSms> $completion) {
    final String _sql = "SELECT * FROM decrypted_sms WHERE serverMsgId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (serverMsgId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, serverMsgId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DecryptedSms>() {
      @Override
      @Nullable
      public DecryptedSms call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
          final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfServerMsgId = CursorUtil.getColumnIndexOrThrow(_cursor, "serverMsgId");
          final int _cursorIndexOfIsSyncedUp = CursorUtil.getColumnIndexOrThrow(_cursor, "isSyncedUp");
          final int _cursorIndexOfIsSyncedDown = CursorUtil.getColumnIndexOrThrow(_cursor, "isSyncedDown");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
          final DecryptedSms _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSender;
            if (_cursor.isNull(_cursorIndexOfSender)) {
              _tmpSender = null;
            } else {
              _tmpSender = _cursor.getString(_cursorIndexOfSender);
            }
            final String _tmpBody;
            if (_cursor.isNull(_cursorIndexOfBody)) {
              _tmpBody = null;
            } else {
              _tmpBody = _cursor.getString(_cursorIndexOfBody);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpServerMsgId;
            if (_cursor.isNull(_cursorIndexOfServerMsgId)) {
              _tmpServerMsgId = null;
            } else {
              _tmpServerMsgId = _cursor.getString(_cursorIndexOfServerMsgId);
            }
            final boolean _tmpIsSyncedUp;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSyncedUp);
            _tmpIsSyncedUp = _tmp != 0;
            final boolean _tmpIsSyncedDown;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsSyncedDown);
            _tmpIsSyncedDown = _tmp_1 != 0;
            final String _tmpDeviceId;
            if (_cursor.isNull(_cursorIndexOfDeviceId)) {
              _tmpDeviceId = null;
            } else {
              _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
            }
            _result = new DecryptedSms(_tmpId,_tmpSender,_tmpBody,_tmpTimestamp,_tmpServerMsgId,_tmpIsSyncedUp,_tmpIsSyncedDown,_tmpDeviceId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getUnsyncedSmsList(final Continuation<? super List<DecryptedSms>> $completion) {
    final String _sql = "SELECT * FROM decrypted_sms WHERE isSyncedUp = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DecryptedSms>>() {
      @Override
      @NonNull
      public List<DecryptedSms> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
          final int _cursorIndexOfBody = CursorUtil.getColumnIndexOrThrow(_cursor, "body");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfServerMsgId = CursorUtil.getColumnIndexOrThrow(_cursor, "serverMsgId");
          final int _cursorIndexOfIsSyncedUp = CursorUtil.getColumnIndexOrThrow(_cursor, "isSyncedUp");
          final int _cursorIndexOfIsSyncedDown = CursorUtil.getColumnIndexOrThrow(_cursor, "isSyncedDown");
          final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
          final List<DecryptedSms> _result = new ArrayList<DecryptedSms>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DecryptedSms _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSender;
            if (_cursor.isNull(_cursorIndexOfSender)) {
              _tmpSender = null;
            } else {
              _tmpSender = _cursor.getString(_cursorIndexOfSender);
            }
            final String _tmpBody;
            if (_cursor.isNull(_cursorIndexOfBody)) {
              _tmpBody = null;
            } else {
              _tmpBody = _cursor.getString(_cursorIndexOfBody);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final String _tmpServerMsgId;
            if (_cursor.isNull(_cursorIndexOfServerMsgId)) {
              _tmpServerMsgId = null;
            } else {
              _tmpServerMsgId = _cursor.getString(_cursorIndexOfServerMsgId);
            }
            final boolean _tmpIsSyncedUp;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSyncedUp);
            _tmpIsSyncedUp = _tmp != 0;
            final boolean _tmpIsSyncedDown;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsSyncedDown);
            _tmpIsSyncedDown = _tmp_1 != 0;
            final String _tmpDeviceId;
            if (_cursor.isNull(_cursorIndexOfDeviceId)) {
              _tmpDeviceId = null;
            } else {
              _tmpDeviceId = _cursor.getString(_cursorIndexOfDeviceId);
            }
            _item = new DecryptedSms(_tmpId,_tmpSender,_tmpBody,_tmpTimestamp,_tmpServerMsgId,_tmpIsSyncedUp,_tmpIsSyncedDown,_tmpDeviceId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLatestSmsTimestamp(final Continuation<? super Long> $completion) {
    final String _sql = "SELECT MAX(timestamp) FROM decrypted_sms";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Long>() {
      @Override
      @Nullable
      public Long call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Long _result;
          if (_cursor.moveToFirst()) {
            final Long _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
