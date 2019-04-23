package ali.naseem.stackme;

import ali.naseem.stackme.datamodels.questions.Questions;
import ali.naseem.stackme.datamodels.tags.Tags;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OverflowService {
    @GET("/2.2/questions?order=desc&site=stackoverflow")
    Call<Questions> listQuestions(@Query("tagged") String tagged, @Query("page") String page, @Query("pagesize") String size, @Query("sort") String sort, @Query("access_token") String token, @Query("key") String key);

    @GET("/2.2/tags?page=1&pagesize=20&order=desc&sort=popular&site=stackoverflow")
    Call<Tags> getTags();
}
