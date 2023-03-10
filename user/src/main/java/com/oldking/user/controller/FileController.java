package com.oldking.user.controller;

import com.oldking.response.AphroditeResponse;
import com.oldking.user.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangzhiyong
 */
@RestController
@Slf4j
@RequestMapping("file")
public class FileController {
    @Autowired
    private FileUtil fileUtil;

    @PostMapping("/upload")
    public AphroditeResponse<String> upload(@RequestPart("file") MultipartFile file) {
        String path = fileUtil.uploadFile(file);
        return AphroditeResponse.success(path);
    }
}
