package faceword;

public class PersistedFaceId 
{
    private int userId;
    private String faceId;

    public PersistedFaceId (int ui, String fi)
    {
        this.userId = ui;
        this.faceId = fi;
    }
    
    public void setUserId(int userId) 
    {
        this.userId = userId;
    }

    public void setFaceId(String faceId) 
    {
        this.faceId = faceId;
    }

    public int getUserId() 
    {
        return userId;
    }

    public String getFaceId() 
    {
        return faceId;
    }  
}
