package com.lpf.jdk8;

import lombok.*;

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
@Builder
public class UserVO {

    private String userId;

    private String userName;

    private Integer userSex;

    private Integer age;


}
