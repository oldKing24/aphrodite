package com.oldking.user.model.user;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.oldking.user.enums.converter.UserStatusConverter;
import com.oldking.user.model.base.BaseImportModel;
import lombok.Data;

import java.util.Date;

/**
 * @author wangzhiyong
 */
@Data
public class TestExcelModel extends BaseImportModel {
    /**
     * EasyExcel使用：导出时忽略该字段
     */
    @ExcelIgnore
    private Integer id;

    @ExcelProperty("用户名")
    private String username;

    /**
     * EasyExcel使用：日期的格式化
     */
    @ExcelProperty("出生日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date birthday;

    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    private String email;

    /**
     * 手机号
     */
    @ExcelProperty("手机号")
    private String tel;

    /**
     * EasyExcel使用：自定义转换器
     */
    @ColumnWidth(10)
    @ExcelProperty(value = "用户状态", converter = UserStatusConverter.class)
    private String userStatus;
}
