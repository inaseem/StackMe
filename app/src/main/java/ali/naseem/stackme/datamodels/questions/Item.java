
package ali.naseem.stackme.datamodels.questions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Item {

    @ColumnInfo
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @ColumnInfo
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @ColumnInfo
    @SerializedName("is_answered")
    @Expose
    private boolean isAnswered;
    @ColumnInfo
    @SerializedName("view_count")
    @Expose
    private long viewCount;
    @ColumnInfo
    @SerializedName("answer_count")
    @Expose
    private long answerCount;
    @ColumnInfo
    @SerializedName("score")
    @Expose
    private long score;
    @ColumnInfo
    @SerializedName("last_activity_date")
    @Expose
    private long lastActivityDate;
    @ColumnInfo
    @SerializedName("creation_date")
    @Expose
    private long creationDate;
    @ColumnInfo
    @SerializedName("last_edit_date")
    @Expose
    private long lastEditDate;

    @PrimaryKey
    @SerializedName("question_id")
    @Expose
    private long questionId;
    @ColumnInfo
    @SerializedName("link")
    @Expose
    private String link;
    @ColumnInfo
    @SerializedName("title")
    @Expose
    private String title;
    @ColumnInfo
    @SerializedName("accepted_answer_id")
    @Expose
    private long acceptedAnswerId;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public long getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(long answerCount) {
        this.answerCount = answerCount;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(long lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(long lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAcceptedAnswerId() {
        return acceptedAnswerId;
    }

    public void setAcceptedAnswerId(long acceptedAnswerId) {
        this.acceptedAnswerId = acceptedAnswerId;
    }

    public Item() {
    }
}
