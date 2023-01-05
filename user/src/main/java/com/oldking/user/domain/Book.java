package com.oldking.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author wangzhiyong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "book")
public class Book {
    /**
     * 主键ID
     */
    @Field(type = FieldType.Keyword)
    private String id;
    /**
     * 文章标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;
    /**
     * 文章内容
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;
    /**
     * 创建时间
     */
    @Field(type = FieldType.Date, pattern = "yyyy-MM-dd HH:mm:ss", format = DateFormat.custom)
    private Date createTime;

}
