package com.mummyding.app.funnychat.Item;

/**
 * Created by mummyding on 15-9-3.
 */
public class UserItem {
    private String userName;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public UserItem(String userName,String userId) {
        this.userName = userName;
        this.userId = userId;
    }
}
