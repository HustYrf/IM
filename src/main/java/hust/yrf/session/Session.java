package hust.yrf.session;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Session {
    private String UserId;
    private String UserName;

    public Session(String userId, String userName) {
        UserId = userId;
        UserName = userName;
    }

    @Override
    public String toString() {
        return UserId + ":" + UserName;
    }
}
