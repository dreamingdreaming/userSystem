package li.ren.bean;


/**
 * 用于分页
 */
public class Page {
    //数据的条数
    private int count ;
    //当前页的页数 默认为1
    private  int nowPage=1;
    //总页数   总页数 应该为 count/maxSize (但要分情况)
    private int totalPage;
    //每页最大容纳数
    private int maxSize=4;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getTotalPage() {
        return count%maxSize==0?count/maxSize:count/maxSize+1;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getStart(){return (nowPage-1)*4 ; }

}
