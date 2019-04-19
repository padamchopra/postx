package com.practikality.postx.models;

import android.net.Uri;

public class TextPostDetails {
    private boolean backgroundImageUsed;
    private Uri backgroundImageUri;
    private int backgroundColor;
    private int textColor;
    private boolean headingUsed;
    private String heading;
    private int headingAlignment;
    private String content;
    private int contentAlignment;
    private boolean byLineUsed;
    private String byLine;

    public boolean isBackgroundImageUsed() {
        return backgroundImageUsed;
    }

    public void setBackgroundImageUsed(boolean backgroundImageUsed) {
        this.backgroundImageUsed = backgroundImageUsed;
    }

    public Uri getBackgroundImageUri() {
        return backgroundImageUri;
    }

    public void setBackgroundImageUri(Uri backgroundImageUri) {
        this.backgroundImageUri = backgroundImageUri;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public boolean isHeadingUsed() {
        return headingUsed;
    }

    public void setHeadingUsed(boolean headingUsed) {
        this.headingUsed = headingUsed;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public int getHeadingAlignment() {
        return headingAlignment;
    }

    public void setHeadingAlignment(int headingAlignment) {
        this.headingAlignment = headingAlignment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContentAlignment() {
        return contentAlignment;
    }

    public void setContentAlignment(int contentAlignment) {
        this.contentAlignment = contentAlignment;
    }

    public boolean isByLineUsed() {
        return byLineUsed;
    }

    public void setByLineUsed(boolean byLineUsed) {
        this.byLineUsed = byLineUsed;
    }

    public String getByLine() {
        return byLine;
    }

    public void setByLine(String byLine) {
        this.byLine = byLine;
    }

    @Override
    public String toString() {
        return "TextPostDetails{" +
                "backgroundImageUsed=" + backgroundImageUsed +
                ", backgroundImageUri=" + backgroundImageUri +
                ", backgroundColor=" + backgroundColor +
                ", textColor=" + textColor +
                ", headingUsed=" + headingUsed +
                ", heading='" + heading + '\'' +
                ", headingAlignment=" + headingAlignment +
                ", content='" + content + '\'' +
                ", contentAlignment=" + contentAlignment +
                ", byLineUsed=" + byLineUsed +
                ", byLine='" + byLine + '\'' +
                '}';
    }
}
