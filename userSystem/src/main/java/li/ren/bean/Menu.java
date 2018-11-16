package li.ren.bean;

/**
 * 菜单实例
 *
 * @author renl
 * @date 2018-11-07
 */
public class Menu {
    private int mid;
    private String mName;
    private String url;

    public Menu() {
    }

    public Menu(int mid, String mName, String url) {
        this.mid = mid;
        this.mName = mName;
        this.url = url;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
