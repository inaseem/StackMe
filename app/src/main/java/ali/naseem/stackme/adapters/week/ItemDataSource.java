package ali.naseem.stackme.adapters.week;

import java.util.List;

import ali.naseem.stackme.BuildConfig;
import ali.naseem.stackme.Utils;
import ali.naseem.stackme.datamodels.questions.Item;
import ali.naseem.stackme.datamodels.questions.Questions;
import ali.naseem.stackme.models.Interest;
import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    public static final int PAGE_SIZE = 20;

    private static final int FIRST_PAGE = 1;

    private String SORT="week";
    private static final String TOKEN=Utils.getInstance().getToken();
    private static final String KEY= BuildConfig.KEY;

    //this will be called once to load the initial data
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {
        List<Interest> list = Utils.getInstance().getDatabase().interestDao().getSelected(true);
        StringBuilder sb = new StringBuilder();
        for (Interest interest : list) {
            sb.append(";").append(interest.getName());
        }
        Utils.getInstance().getService().listQuestions(sb.toString().substring(1), "1", String.valueOf(PAGE_SIZE), SORT,TOKEN,KEY)
                .enqueue(new Callback<Questions>() {
                    @Override
                    public void onResponse(Call<Questions> call, Response<Questions> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getItems(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<Questions> call, Throwable t) {

                    }
                });
    }

    //this will load the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        List<Interest> list = Utils.getInstance().getDatabase().interestDao().getSelected(true);
        StringBuilder sb = new StringBuilder();
        for (Interest interest : list) {
            sb.append(";").append(interest.getName());
        }
        Utils.getInstance().getService().listQuestions(sb.toString().substring(1), String.valueOf(params.key), String.valueOf(PAGE_SIZE), SORT,TOKEN,KEY)
                .enqueue(new Callback<Questions>() {
                    @Override
                    public void onResponse(Call<Questions> call, Response<Questions> response) {
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                callback.onResult(response.body().getItems(), adjacentKey);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Questions> call, Throwable t) {

                    }
                });
    }

    //this will load the next page
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        List<Interest> list = Utils.getInstance().getDatabase().interestDao().getSelected(true);
        StringBuilder sb = new StringBuilder();
        for (Interest interest : list) {
            sb.append(";").append(interest.getName());
        }
        Utils.getInstance().getService().listQuestions(sb.toString().substring(1), String.valueOf(params.key), String.valueOf(PAGE_SIZE), SORT,TOKEN,KEY)
                .enqueue(new Callback<Questions>() {
                    @Override
                    public void onResponse(Call<Questions> call, Response<Questions> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Integer key = response.body().isHasMore() ? params.key + 1 : null;
                                callback.onResult(response.body().getItems(), key);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Questions> call, Throwable t) {

                    }
                });
    }
}
