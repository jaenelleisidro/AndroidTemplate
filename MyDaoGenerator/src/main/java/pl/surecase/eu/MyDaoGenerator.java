package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(3, "securitywizards.herobo.com.androidtemplate.model.dataaccesslayer.greendao");
        Entity box = schema.addEntity("Box");
        box.addIdProperty();
        box.addStringProperty("name");
        box.addIntProperty("slots");
        box.addStringProperty("description");
        String outputDir="app/src/main/java";
        new DaoGenerator().generateAll(schema,outputDir);
//TODO : running it via gradle doesnt work. so im running it temporarily here directly instead. in the future, we need to fix this
//        new DaoGenerator().generateAll(schema, args[0]);
    }
}
