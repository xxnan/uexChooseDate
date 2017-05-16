package com.bigkoo.pickerview.utils;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import com.xxnan.choosedate.R;

import android.view.Gravity;



/**
 * Created by Sai on 15/8/9.
 */
public class PickerViewAnimateUtil {
    private static final int INVALID = -1;
    /**
     * Get default animation resource when not defined by the user
     *
     * @param gravity       the gravity of the dialog
     * @param isInAnimation determine if is in or out animation. true when is is
     * @return the id of the animation resource
     */
    public static int getAnimationResource(int gravity, boolean isInAnimation) {
        switch (gravity) {
            case Gravity.BOTTOM:
                return isInAnimation ?EUExUtil.getResAnimID("pickerview_slide_in_bottom") : EUExUtil.getResAnimID("pickerview_slide_out_bottom");
        }
        return INVALID;
    }
}
