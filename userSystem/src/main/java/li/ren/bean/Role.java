package li.ren.bean;

/**
 * @author renl
 * @date 2018-11-07
 * 角色实例类
 */
public class Role {
    private int rid ;
    private  String rName;

    public Role() {
    }

    public Role(int rid, String rName) {
        this.rid = rid;
        this.rName = rName;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }
}
