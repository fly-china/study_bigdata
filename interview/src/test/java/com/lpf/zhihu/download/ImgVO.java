package com.lpf.zhihu.download;

import java.util.List;

/**
 * 导出图片工具类
 *
 * @author lipengfei
 * @date 2019-06-20 11:25
 **/
public class ImgVO {
    private Long id;

    private List<String> linkList;

    private String questionId;

    private String questionTitle;

    private String answerId;

    private String answerAuthorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<String> linkList) {
        this.linkList = linkList;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswerAuthorName() {
        return answerAuthorName;
    }

    public void setAnswerAuthorName(String answerAuthorName) {
        this.answerAuthorName = answerAuthorName;
    }
}
