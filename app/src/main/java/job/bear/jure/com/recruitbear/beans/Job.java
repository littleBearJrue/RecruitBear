package job.bear.jure.com.recruitbear.beans;

/**
 * Created by jrue on 2015/9/21.
 */
public class Job {

    private int id;
    private String name;
    private String address;
    private String company;
    private String holdTime;

    public Job(int id, String name, String address, String company, String holdTime) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.company = company;
        this.holdTime = holdTime;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", company='" + company + '\'' +
                ", holdTime='" + holdTime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(String holdTime) {
        this.holdTime = holdTime;
    }
}
