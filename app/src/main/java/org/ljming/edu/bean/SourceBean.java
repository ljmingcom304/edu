package org.ljming.edu.bean;

import java.io.Serializable;

/**
 * Title: SourceBean
 * <p>
 * Description:课程下工具条目
 * </p>
 * Author: Jming.L
 * Date: 2018/9/24 20:38
 * Copyright: Jming.Liang All rights reserved.
 */
public class SourceBean implements Serializable {
    private static final long serialVersionUID = 2526557086140851766L;

    public String name;
    public int resId;

    public SourceBean(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }
}
