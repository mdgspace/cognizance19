package com.mdg.iitr.cognizance19.utils;

import android.text.Editable;
import android.text.Html;

import org.xml.sax.XMLReader;

public class HTMLTagHandler implements Html.TagHandler {
    @Override
    public void handleTag(boolean opening, String tag, Editable output,
                          XMLReader xmlReader) {
        if (tag.equals("li") && !opening) output.append("\n");
    }
}

