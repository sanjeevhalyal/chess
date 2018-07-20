package UserInsatance;

public class UserInfo {
    private String name;
    private String uuid;
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserInfo(String name, String uuid, String type) {
        this.name = name;
        this.uuid = uuid;
        this.type=type;

    }
    public String getuuid()
    {
        return this.uuid;
    }

    public String getuser()
    {
        return this.name;
    }
}
