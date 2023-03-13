package com.oldking.user.request.importExcel;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wangzhiyong
 */
@Setter
@Getter
public class CourseImportRequest extends BaseImportRequest {

    public static void main(String[] args) {
        CourseImportRequest request = new CourseImportRequest();
        request.setType("course");
        String s = JSONObject.toJSONString(request);
        System.out.println(s);
    }
}
