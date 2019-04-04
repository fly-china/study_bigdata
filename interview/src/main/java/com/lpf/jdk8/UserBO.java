package com.lpf.jdk8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class UserBO {

    private String userId;

    private String userName;

    private Integer sex;


}
