//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : GridPicture.java
//  @ Date : 2018/12/4
//  @ Author : hupei
//
//


package com.lyl.gridPictureViewLib.view;

import java.io.File;

/** */
public interface GridPicture {


    /** */
    void addPicture(File file);

    void addPicture(int resId);

    /** */
    void addPicture(String path);

    /** */
    void removePicture(int position);
}
