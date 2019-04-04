package com.lpf.jdk8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 用户类
 *
 * @author lipengfei
 * @create 2019-04-02 11:35
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVO {

    private String userId;

    private String userName;

    private Integer userSex;

    private Integer age;


}
