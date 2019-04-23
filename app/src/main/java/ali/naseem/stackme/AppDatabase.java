package ali.naseem.stackme;

import ali.naseem.stackme.dao.InterestDao;
import ali.naseem.stackme.models.Interest;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Interest.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract InterestDao interestDao();
}

