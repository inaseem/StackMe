package ali.naseem.stackme.dao;

import java.util.List;

import ali.naseem.stackme.datamodels.questions.Item;
import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

public interface QuestionsDao {
    @Query("SELECT * FROM questions")
    LiveData<List<Item>> getAll();

    @Insert
    void insertAll(Item... items);

    @Update
    void update(Item item);

    @Delete
    void delete(Item item);

    @Query("DELETE from questions")
    void deleteAll();
}
