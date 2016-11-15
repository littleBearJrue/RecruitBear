package job.bear.jure.com.recruitbear.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrue on 2015/9/21.
 */
public class Result {

    private List<Job> info = new ArrayList<>();

    public Result(List<Job> info) {
        this.info = info;
    }


    public List<Job> getInfo() {
        return info;
    }

    public void setInfo(List<Job> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "info=" + info +
                '}';
    }

}
