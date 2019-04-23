package ali.naseem.stackme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.flexbox.AlignContent;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;

import ali.naseem.stackme.adapters.TagsAdapter;
import ali.naseem.stackme.adapters.TagsViewModel;
import ali.naseem.stackme.datamodels.tags.Item;
import ali.naseem.stackme.datamodels.tags.Tags;
import ali.naseem.stackme.models.Interest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class UserInterestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button done;
    private ArrayList<Item> items;
    private TagsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        recyclerView = findViewById(R.id.recyclerView);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setAlignItems(AlignContent.FLEX_START);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        recyclerView.setLayoutManager(layoutManager);
        items = new ArrayList<>();
        adapter = new TagsAdapter(items, this);
        recyclerView.setAdapter(adapter);
        done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 0;
                Utils.getInstance().getDatabase().interestDao().deleteAll();
                for (Item tag : items) {
                    if (tag.isSelected()) {
                        Interest newInterest = new Interest(i, tag.getName().trim(), false);
                        if (i == 0) {
                            newInterest.setSelected(true);
                        }
                        Utils.getInstance().getDatabase().interestDao().insertAll(newInterest);
                        i++;
                    }

                }
                Intent intent = new Intent(UserInterestActivity.this, QuestionListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        TagsViewModel viewModel = ViewModelProviders.of(this).get(TagsViewModel.class);
        viewModel.getTags().observe(this, new Observer<Tags>() {
            @Override
            public void onChanged(Tags tags) {
                items.addAll(tags.getItems());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
