package org.ljming.edu.bean;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Jming.L on 2018/9/29.
 */

public class ACacheBean implements Serializable {

    private static final long serialVersionUID = -3742518186086857320L;

    public File unitFile;
    public List<MenuBean> allBeans;
    public List<MenuBean> curBeans;

}
