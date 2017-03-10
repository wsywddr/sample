package com.wsywddr.sample.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.wsywddr.sample.util.glide.CircleTransform;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
    /**
     * 将时间戳转为代表"距现在多久之前"的字符串
     *
     * @param timeStr 时间戳
     * @return
     */
    public static String getStandardDate(String timeStr) {

        StringBuffer sb = new StringBuffer();

        long t = Long.parseLong(timeStr);
        long time = System.currentTimeMillis() - (t * 1000);
        long mill = (long) Math.ceil(time / 1000);//秒前

        long minute = (long) Math.ceil(time / 60 / 1000.0f);// 分钟前

        long hour = (long) Math.ceil(time / 60 / 60 / 1000.0f);// 小时

        long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000.0f);// 天前

        if (day - 1 > 0) {
            sb.append(day + "天");
        } else if (hour - 1 > 0) {
            if (hour >= 24) {
                sb.append("1天");
            } else {
                sb.append(hour + "小时");
            }
        } else if (minute - 1 > 0) {
            if (minute == 60) {
                sb.append("1小时");
            } else {
                sb.append(minute + "分钟");
            }
        } else if (mill - 1 > 0) {
            if (mill == 60) {
                sb.append("1分钟");
            } else {
                sb.append(mill + "秒");
            }
        } else {
            sb.append("刚刚");
        }
        if (!sb.toString().equals("刚刚")) {
            sb.append("前");
        }
        return sb.toString();
    }

    /**
    * 得到倒计时小时 20小时10分40秒
    *
    * chenwei
    * email wsywddr@163.com
    * created 17-1-13 上午10:35
    */
    public static String getDaoTime(String timeStr) {
        String result = "";
        long t = Long.parseLong(timeStr);
        long time = System.currentTimeMillis() - (t * 1000);
        long miss = (long) Math.ceil(time / 1000);//秒前

        String hh = miss / 3600 + "";
        String mm = (miss % 3600) / 60 + "";
        String ss = (miss % 3600) % 60 + "";
        result = hh + "," + mm + "," + ss + "";
        return result.replace("-", "");
    }

    /**
     * 得到倒计时 10天20小时10分
     *
     * chenwei
     * email wsywddr@163.com
     * created 17-1-13 上午10:35
     */
    public static String getDayHourToNow(String timeStr, Context context) {
        String result = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String nowdate = df.format(new java.util.Date());
            Date d1 = df.parse(nowdate);
            Date d2 = df.parse(TimeStamp2Date(timeStr, "yyyy-MM-dd HH:mm:ss"));

            long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别

            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);

            result = "" + days +  "天" + hours + "小时" + minutes + "分";
        }
        catch (Exception e) {
        }
        return result.replace("-", "");
    }

    /**
     * 时间戳 格式化成日期
     *
     * chenwei
     * email wsywddr@163.com
     * created 17-1-13 上午10:35
     */
    public static String TimeStamp2Date(String timestampString, String formats) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));
        return date;
    }


    /*获取系统时间 格式为："yyyy/MM/dd "*/
    public static String getCurrentDate() {
        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sf.format(d);
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try{
            date = sdf.parse(time);
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime()/1000;
    }

    public static String getImageUrl(String key, String width, String height)
    {
        return AppConfig.QiNiuUrl+key+"?imageView2/1/w/"+width+"/h/"+height;
    }

    public static void loadImageUrlToImageView(Context context, ImageView mImageView, String img_url)
    {
        Glide.with(context).load(img_url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(com.jaydenxiao.common.R.drawable.ic_image_loading)
//                .error(R.mipmap.ic_empty_picture)
//                .centerCrop()
                .crossFade().into(mImageView);
    }

    public static void loadImageUrlToCircleImageView(Context context, ImageView mImageView, String img_url)
    {
        Glide.with(context).load(img_url)
                .transform(new CircleTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(com.jaydenxiao.common.R.drawable.ic_image_loading)
                .crossFade().into(mImageView);
    }

    public static boolean isEmpty(String text) {
        if (text != null && !text.trim().equals("")&& !TextUtils.isEmpty(text)) {
            return false;
        }else{
            return true;
        }
    }

    public static void showToast(Context context, String text) {
        if (text != null && !text.trim().equals("")) {
            SuperActivityToast.create(context, new Style(), Style.TYPE_STANDARD)
                    .setText(text)
                    .setDuration(Style.DURATION_SHORT)
                    .setFrame(Style.FRAME_KITKAT)
                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.GREY))
                    .setAnimations(Style.ANIMATIONS_POP).show();
//            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    /**
    * 修改输入框 提示文字大小
    *
    * chenwei
    * email wsywddr@163.com
    * created 17-1-13 上午10:39
    */
    public static void setHintTextSize(EditText editText, String hintText, int textSize) {
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(hintText);
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(textSize, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置hint
        editText.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
    }

    public static String getPackageName(Context context) {
        String packageName = null;
        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            packageName = pi.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageName;
    }

    public static String getVersionName(Context context) throws Exception
    {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(context),0);
        String version = packInfo.versionName;
        return version;
    }

    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 检查网络是否可用
     *
     * @param paramContext
     * @return
     */
    public static boolean isNetConnected(Context paramContext) {
        boolean i = false;
        NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
            return true;
        return false;
    }
}
