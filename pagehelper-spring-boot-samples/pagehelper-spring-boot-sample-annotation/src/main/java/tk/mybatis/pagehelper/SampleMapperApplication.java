/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package tk.mybatis.pagehelper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageRowBounds;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.pagehelper.domain.User;
import tk.mybatis.pagehelper.mapper.UserMapper;

import java.util.List;

@MapperScan(basePackages = "tk.mybatis.pagehelper")
@SpringBootApplication
public class SampleMapperApplication implements CommandLineRunner {

    @Autowired
    private UserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(SampleMapperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PageHelper.startPage(1, 20).disableAsyncCount();
        List<User> users = userMapper.findAll();
        System.out.println("Total: " + ((Page) users).getTotal());
        for (User user : users) {
            System.out.println("Name: " + user.getName());
        }

        PageHelper.orderBy("id desc");
        users = userMapper.findAll();
        System.out.println("Total: " + ((Page) users).getTotal());
        for (User user : users) {
            System.out.println("Name: " + user.getName());
        }

        PageRowBounds rowBounds = new PageRowBounds(3, 5);
        users = userMapper.findAll(rowBounds);
        System.out.println("Total: " + rowBounds.getTotal());
        for (User user : users) {
            System.out.println("Name: " + user.getName());
        }
    }

}
