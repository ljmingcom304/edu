package org.ljming.edu.bean;

import java.io.File;
import java.io.Serializable;

/**
 * Title: MenuBean
 * <p>
 * Description:目录树
 * </p>
 * Author: Jming.L
 * Date: 2018/9/24 22:03
 * Copyright: Jming.Liang All rights reserved.
 */
public class MenuBean implements Serializable {
    private static final long serialVersionUID = -3977178286877204891L;
    public int id;          //ID
    public int parentId;    //父ID，根目录父ID为0
    public File file;        //所代表的文件
    public int level;       //级别课程0年级1单元2
    public boolean isExpend;  //是否展开
    public boolean isShow;    //是否显示

    public MenuBean(int id, int parentId, File file, int level, boolean isExpend) {
        this.id = id;
        this.parentId = parentId;
        this.file = file;
        this.level = level;
        this.isExpend = isExpend;
    }
}
