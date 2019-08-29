package com.zhang.core.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Created by 谭健 on 2019/6/10. 星期一. 10:41.
 * © All Rights Reserved.
 */

@Data
public abstract class BasePO implements Serializable {

    private static final long serialVersionUID = 1379444456611663894L;
    protected Long pkId;

    protected Date gmtCreate;
    protected Date gmtModified;
    protected String remark;
    protected Integer sort;
    protected Boolean deleted;

}
