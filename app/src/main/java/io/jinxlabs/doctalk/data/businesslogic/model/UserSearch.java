
package io.jinxlabs.doctalk.data.businesslogic.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSearch {

    @SerializedName("total_count")
    @Expose
    private int totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<User> items = null;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }

}
