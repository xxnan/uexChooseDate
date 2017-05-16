package com.xxnan.choosedate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.zywx.wbpalmstar.engine.EBrowserView;
import org.zywx.wbpalmstar.engine.universalex.EUExBase;
import org.zywx.wbpalmstar.engine.universalex.EUExCallback;

import com.bigkoo.pickerview.TimePickerView;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

public class EUExChooseDate extends EUExBase{
	public static final String CALLBACK_DATEPICKER = "uexChooseDate.cbOpenDatePicker";
	private Context mContext;
	public EUExChooseDate(Context context, EBrowserView inParent) {
		super(context,inParent);
		this.mContext=context;
	}

	public void openDatePicker(String[] params) {
        int inYear, inMonth, inDay = 0;
        if (params.length == 3) {
            try {
                inYear = Integer.parseInt(params[0].trim());
                inMonth = Integer.parseInt(params[1].trim()) - 1;
                inDay = Integer.parseInt(params[2].trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // 默认当前日期
                Calendar calendar = Calendar.getInstance();
                inYear = calendar.get(Calendar.YEAR);
                inMonth = calendar.get(Calendar.MONTH);
                inDay = calendar.get(Calendar.DAY_OF_MONTH);
            }
        } else {// 默认当前日期
            Calendar calendar = Calendar.getInstance();
            inYear = calendar.get(Calendar.YEAR);
            inMonth = calendar.get(Calendar.MONTH);
            inDay = calendar.get(Calendar.DAY_OF_MONTH);
        }
        Log.i("date", "inYear2=" + inYear + ",inMonth2=" + inMonth + ",inDay2="
                + inDay);
        final int[] dateSet = new int[] { inYear, inMonth, inDay };
        final Calendar calendar =Calendar.getInstance();
        calendar.set(inYear, inMonth, inDay );
        ((Activity) mContext).runOnUiThread(new Runnable() {

            @Override
            public void run() {
            	TimePickerView pvTime = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        long nowTime = date.getTime();
                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(nowTime);
                        String dateString=dateFormat.format(cal.getTime());
                        String []s=dateString.split("-");
                        
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject
                                    .put(EUExCallback.F_JK_YEAR, Integer.parseInt(s[0]));
                            jsonObject.put(EUExCallback.F_JK_MONTH,
                            		Integer.parseInt(s[1]));
                            jsonObject.put(EUExCallback.F_JK_DAY,
                            		Integer.parseInt(s[2]));
                            Log.i("jsonObject.toString()",
                                    jsonObject.toString() + "----");
                            jsCallback(CALLBACK_DATEPICKER, 0,
                                    EUExCallback.F_C_JSON,
                                    jsonObject.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }).setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                        .setLabel("","","","","","")
                        .build();

                pvTime.setDate(calendar);//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            }  
        });
    }
	@Override
	protected boolean clean() {
		// TODO Auto-generated method stub
		return false;
	}

}
