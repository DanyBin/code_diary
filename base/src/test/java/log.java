/**
 * @ClassName log
 * @Author bin
 * @Date 2020/11/12 上午11:23
 * @Decr TODO
 * @Link TODO
 **/
public class log {

    private Integer id;
    private Integer campaignId;
    private Integer throttlingId;
    private Integer action;
    private String extraParams;
    private Integer status;

    public log(Integer id, Integer campaignId, Integer throttlingId, Integer action, String extraParams, Integer status) {
        this.id = id;
        this.campaignId = campaignId;
        this.throttlingId = throttlingId;
        this.action = action;
        this.extraParams = extraParams;
        this.status = status;
    }
}
