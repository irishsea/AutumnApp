package irishsea.autumnapp.security;

import java.util.Date;

public class TokenPayload {
    private Long userId;
    private String email;
    private Date creationTime;

    public TokenPayload(Long userId, String email, Date creationTime) {
        this.userId = userId;
        this.email = email;
        this.creationTime = creationTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
