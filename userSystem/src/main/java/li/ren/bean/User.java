package li.ren.bean;

/**
 * 用户实例类
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String realname;
    private String email;
    private String roleName;
    private String images;
    private int age;
    private int sex;


    public User(int id, String username, String password, String realname, String email, String roleName, String images, int age, int sex) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.email = email;
        this.roleName = roleName;
        this.images = images;
        this.age = age;
        this.sex = sex;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getRember() {
        return rember;
    }

    public void setRember(int rember) {
        this.rember = rember;
    }

    private int rember;


    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //有参构造



    //无参构造
    public User() {
    }
}
