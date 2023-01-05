package com.oldking.todo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oldking.todo.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhiyong
 */
@SpringBootTest(classes = TodoApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class RaffleTest {
    private static final String hostUrl = "-";
    private static final String P_USER_ID = "10086";
    private static final String P_USER_NAME = "10086";
    private static final String C_USER_ID = "20220831";
    private static final String C_USER_ID_C = "20220901";

    @Resource(name = "restTemplateIgnoreSSL")
    private RestTemplate restTemplate;

    @Test
    public void test() {
        String url = "https://www.zhihu.com/api/v3/feed/topstory/hot-lists/total?limit=50&desktop=true";
        ResponseEntity<String> response2 = restTemplate.exchange(url, HttpMethod.GET, null, String.class, (Object) null);
        String body = response2.getBody();
        JSONObject jsonObject = JSON.parseObject(body);
        Object data = jsonObject.get("data");
        JSONArray jsonArray = JSON.parseArray(data.toString());
        for (Object o : jsonArray) {
            JSONObject item = JSON.parseObject(o.toString());
            JSONArray children = item.getJSONArray("children");
            JSONObject thumbnail = JSON.parseObject(children.get(0).toString());
            String thumbnailUrl = thumbnail.get("thumbnail").toString();
            log.info("url-----------------" + thumbnailUrl);
            Object target = item.get("target");
            JSONObject targetJson = JSON.parseObject(target.toString());
            Object title = targetJson.get("title");
            log.info("响应-----------------" + StringEscapeUtils.unescapeJava(title.toString()));
            String url1 = targetJson.get("url").toString();
            log.info("响应url1-----------------" + url1);
            int i = url1.lastIndexOf("/");
            String substring = url1.substring(i + 1);
            log.info("截取-----------------" + "https://www.zhihu.com/question/" + substring);
        }
    }

    @Test
    public void raffling() {
        String url = hostUrl + "-";
        for (int i = 0; i < 1; i++) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("dt", "20190225");
            paramMap.put("ht", "10");
            HttpHeaders headers = new HttpHeaders();
            headers.set("-", C_USER_ID + i);
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(null, headers);
            ResponseEntity<String> response2 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, paramMap);
            log.info(JSONUtil.safeToJson(response2));
        }
    }

    @Test
    public void acquire() {
        String url = hostUrl + "-";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("-", C_USER_ID);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(null, headers);
        for (int i = 0; i < 10; i++) {
            ResponseEntity<String> response2 = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, paramMap);
            String body = response2.getBody();
            log.info(JSONUtil.safeToJson(body));
        }
    }
}
