package utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Shamyyoun on 3/16/2015.
 */
public class DrawableUtil {
    /**
     * method, used to return id of drawable from its  name
     */
    public static int getDrawableId(Context context, String name) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier(name, "drawable", context.getPackageName());
        return id;
    }
}
