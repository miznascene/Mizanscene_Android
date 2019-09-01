package com.example.mizansen.Helper;

import android.content.Context;
import android.content.res.Resources;

import io.paperdb.Paper;

public class LanguageHelper {


    public Context GetLanguage(Context context){
        Paper.init(context);

        String language = Paper.book().read("language");
        if (language == null)
            Paper.book().write("language", "fa");

       return updateView((String) Paper.book().read("language"),context);
    }


    private Context updateView(String language,Context _context) {
        Context context = LocaleHelper.setLocale(_context, language);
        Resources resources = context.getResources();

        return context;

    }

}
