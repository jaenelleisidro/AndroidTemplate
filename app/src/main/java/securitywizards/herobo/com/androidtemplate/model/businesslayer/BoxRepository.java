package securitywizards.herobo.com.androidtemplate.model.businesslayer;

import android.content.Context;

import java.util.List;

import securitywizards.herobo.com.androidtemplate.model.dataaccesslayer.greendao.Box;
import securitywizards.herobo.com.androidtemplate.model.dataaccesslayer.greendao.BoxDao;
import securitywizards.herobo.com.androidtemplate.model.dataaccesslayer.greendao.DaoSession;


/**
 * Created by surecase on 19/03/14.
 */
public class BoxRepository {
    DaoSession daoSession;
    public BoxRepository(DaoSession daoSession){
        this.daoSession=daoSession;
    }
    public void insertOrUpdate(Context context, Box box) {
        getBoxDao(context).insertOrReplace(box);
    }

    public void clearBoxes(Context context) {
        getBoxDao(context).deleteAll();
    }

    public void deleteBoxWithId(Context context, long id) {
        getBoxDao(context).delete(getBoxForId(context, id));
    }

    public Box getBoxForId(Context context, long id) {
        return getBoxDao(context).load(id);
    }

    public List<Box> getAllBoxes(Context context) {
        return getBoxDao(context).loadAll();
    }

    public BoxDao getBoxDao(Context c) {
        return daoSession.getBoxDao();
    }
}
