package mario.rafeek.etba3ny.modules;

/**
 * Created by rifo on 10/11/16.
 */
public class tranimModule {
    private String id;
    private String name;
    private String folder;

    public tranimModule(String id, String name, String folder) {
        this.name = name;
        this.id = id;
        this.folder = folder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
