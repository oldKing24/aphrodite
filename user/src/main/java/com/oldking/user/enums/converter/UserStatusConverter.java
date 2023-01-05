package com.oldking.user.enums.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @author wangzhiyong
 */
public class UserStatusConverter implements Converter<String> {
    private static final String NORMAL = "正常";
    private static final String LOG_OFF = "注销";
    private static final String FROZEN = "冻结";

    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        // 从Cell中读取数据
        String userStatus = cellData.getStringValue();
        // 判断Excel中的值，将其转换为预期的数值
        if (NORMAL.equals(userStatus)) {
            return "0";
        } else if (LOG_OFF.equals(userStatus)) {
            return "1";
        } else if (FROZEN.equals(userStatus)) {
            return "2";
        }
        return null;
    }

    @Override
    public CellData convertToExcelData(String data, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        // 判断实体类中获取的值，转换为Excel预期的值，并封装为CellData对象
        if (data == null) {
            return new CellData<>("");
        } else if ("0".equals(data)) {
            return new CellData<>(NORMAL);
        } else if ("1".equals(data)) {
            return new CellData<>(LOG_OFF);
        } else if ("2".equals(data)) {
            return new CellData<>(FROZEN);
        }
        return new CellData<>("");
    }
}
