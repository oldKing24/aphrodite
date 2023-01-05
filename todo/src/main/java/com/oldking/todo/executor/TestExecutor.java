package com.oldking.todo.executor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oldking.todo.domain.PInformation;
import com.oldking.todo.repository.InformationRepository;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhiyong
 */
@Component
@Slf4j
public class TestExecutor {
    @Resource(name = "restTemplateIgnoreSSL")
    private RestTemplate restTemplate;
    @Autowired
    private InformationRepository informationRepository;

    @XxlJob("todoTest")
    public void test() {
        log.info("xxl-test>>>>>>>>");
        testInformation();
    }

    private void testInformation() {
        String url = "https://www.zhihu.com/api/v3/feed/topstory/hot-lists/total?limit=50&desktop=true";
        ResponseEntity<String> response2 = restTemplate.exchange(url, HttpMethod.GET, null, String.class, (Object) null);
        String body = response2.getBody();
        JSONObject jsonObject = JSON.parseObject(body);
        Object data = jsonObject.get("data");
        JSONArray jsonArray = JSON.parseArray(data.toString());
        List<PInformation> list = new ArrayList<>();
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
            PInformation pInformation = new PInformation();
            pInformation.setCategoryId(1L);
            pInformation.setTitle(StringEscapeUtils.unescapeJava(title.toString()));
            pInformation.setSummary(StringEscapeUtils.unescapeJava(title.toString()));
            pInformation.setImg(thumbnailUrl);
            pInformation.setLinkUrl("https://www.zhihu.com/question/" + substring);
            pInformation.setCommentCount(0L);
            list.add(pInformation);
        }
        informationRepository.batchSave(list);
    }
}
