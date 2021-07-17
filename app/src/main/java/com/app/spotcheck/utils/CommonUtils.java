package com.app.spotcheck.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;

import com.app.spotcheck.R;

public class CommonUtils {
    public static String setDevicePart(String mainName, String partName, Context context){
        String lable = mainName+"/"+partName;
        int preIndex = lable.indexOf("/");
        SpannableString styledText = new SpannableString(lable);
        styledText.setSpan(new TextAppearanceSpan(context, R.style.exception_part_style1), 0, preIndex, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(context, R.style.exception_part_style2), preIndex, lable.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return styledText.toString();
    }
}
