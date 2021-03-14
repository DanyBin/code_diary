import java.sql.Timestamp;

/**
 * @ClassName SceneData
 * @Author bin
 * @Date 2020/11/11 下午8:12
 * @Decr TODO
 * @Link TODO
 **/
public class SceneData {

    private String sceneId;

    private String sceneKey;

    private String extraParams;

    private Timestamp lastModify;

    private int valid;

    public SceneData(String sceneId, String sceneKey, String extraParams, Timestamp lastModify, int valid) {
        this.sceneId = sceneId;
        this.sceneKey = sceneKey;
        this.extraParams = extraParams;
        this.lastModify = lastModify;
        this.valid = valid;
    }

    public String getSceneId() {
        return sceneId;
    }

    public SceneData setSceneId(String sceneId) {
        this.sceneId = sceneId;
        return this;
    }

    public String getSceneKey() {
        return sceneKey;
    }

    public SceneData setSceneKey(String sceneKey) {
        this.sceneKey = sceneKey;
        return this;
    }

    public String getExtraParams() {
        return extraParams;
    }

    public SceneData setExtraParams(String extraParams) {
        this.extraParams = extraParams;
        return this;
    }

    public Timestamp getLastModify() {
        return lastModify;
    }

    public SceneData setLastModify(Timestamp lastModify) {
        this.lastModify = lastModify;
        return this;
    }

    public int getValid() {
        return valid;
    }

    public SceneData setValid(int valid) {
        this.valid = valid;
        return this;
    }
}
