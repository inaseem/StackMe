
package ali.naseem.stackme.datamodels.tags;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("has_synonyms")
    @Expose
    private boolean hasSynonyms;
    @SerializedName("is_moderator_only")
    @Expose
    private boolean isModeratorOnly;
    @SerializedName("is_required")
    @Expose
    private boolean isRequired;
    @SerializedName("count")
    @Expose
    private long count;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("selected")
    @Expose
    private boolean selected;

    public boolean isHasSynonyms() {
        return hasSynonyms;
    }

    public void setHasSynonyms(boolean hasSynonyms) {
        this.hasSynonyms = hasSynonyms;
    }

    public boolean isIsModeratorOnly() {
        return isModeratorOnly;
    }

    public void setIsModeratorOnly(boolean isModeratorOnly) {
        this.isModeratorOnly = isModeratorOnly;
    }

    public boolean isIsRequired() {
        return isRequired;
    }

    public void setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
