package com.oldking.user.request.export;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wangzhiyong
 */
@Setter
@Getter
public class UserExportRequest extends BaseExportRequest{

    public static void main(String[] args) {
        UserExportRequest request = new UserExportRequest();
        request.setType("course");
        String s = JSONObject.toJSONString(request);
        System.out.println(s);
    }
}
