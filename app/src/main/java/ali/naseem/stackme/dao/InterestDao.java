package ali.naseem.stackme.dao;

import java.util.List;

import ali.naseem.stackme.models.Interest;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface InterestDao {
    @Query("SELECT * FROM interests")
    LiveData<List<Interest>> getAll();

    @Query("SELECT * FROM interests WHERE uid IN (:id)")
    List<Interest> loadAllByIds(int[] id);

    @Query("SELECT * FROM interests WHERE selected = (:selected)")
    List<Interest> getSelected(boolean selected);

    @Insert
    void insertAll(Interest... interests);

    @Update
    void update(Interest interest);

    @Delete
    void delete(Interest interest);

    @Query("DELETE from interests")
    void deleteAll();
}

