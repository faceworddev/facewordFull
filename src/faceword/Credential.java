package faceword;

public class Credential
{
    private String username;
    private String password;
    private String url;
    private int userId;
    private int credentialId;
    
    public Credential(String u, String p, String url, int uid, int cid)
    {
        this.username = u;
        this.password = p;
        this.url = url;
        this.userId = uid;
        this.credentialId = cid;
    }
    
    public Credential(String un, String pw, String url)
    {
        this.username = un;
        this.url = url;
        this.password = pw;
    }
    
    public Credential(){}

    public String getUsername() 
    {
        return username;
    }

    public String getPassword() 
    {
        return password;
    }

    public String getUrl() 
    {
        return url;
    }

    public int getUserId() 
    {
        return userId;
    }

    public int getCredentialId() 
    {
        return credentialId;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public void setUrl(String url) 
    {
        this.url = url;
    }

    public void setUserId(int userId) 
    {
        this.userId = userId;
    }

    public void setCredentialId(int credentialId) 
    {
        this.credentialId = credentialId;
    }
}
