
package ali.naseem.stackme.datamodels.questions;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questions {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("has_more")
    @Expose
    private boolean hasMore;
    @SerializedName("quota_max")
    @Expose
    private long quotaMax;
    @SerializedName("quota_remaining")
    @Expose
    private long quotaRemaining;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public long getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(long quotaMax) {
        this.quotaMax = quotaMax;
    }

    public long getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(long quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }

}
