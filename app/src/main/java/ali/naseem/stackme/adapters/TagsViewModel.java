package ali.naseem.stackme.adapters;

import ali.naseem.stackme.Utils;
import ali.naseem.stackme.datamodels.tags.Tags;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TagsViewModel extends ViewModel {
    private MutableLiveData<Tags> tags;

    public LiveData<Tags> getTags() {
        if (tags == null) {
            tags = new MutableLiveData<Tags>();
            loadTags();
        }
        return tags;
    }

    private void loadTags() {
        Utils.getInstance().getService().getTags()
                .enqueue(new Callback<Tags>() {
                    @Override
                    public void onResponse(Call<Tags> call, Response<Tags> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                tags.setValue(response.body());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Tags> call, Throwable t) {

                    }
                });
    }

}
