package faceword;

import java.awt.Image;

public class User 
{
    private String lastName;
    private String firstName;
    private int userId;
    private Image image;
    
    public User(String ln, String fn, int uid, Image i)
    {
        this.lastName = ln;
        this.firstName = fn;
        this.userId = uid;
        this.image = i;
    }
    
    public User(){}

    public String getLastName() 
    {
        return lastName;
    }

    public String getFirstName() 
    {
        return firstName;
    }

    public int getUserId() 
    {
        return userId;
    }

    public Image getImage() 
    {
        return image;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public void setUserId(int userId) 
    {
        this.userId = userId;
    }

    public void setImage(Image image) 
    {
        this.image = image;
    }
}
