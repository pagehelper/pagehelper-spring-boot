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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.pagehelper.domain.Country;
import tk.mybatis.pagehelper.mapper.CountryMapper;

import java.util.List;

@SpringBootApplication
public class SampleXmlApplication implements CommandLineRunner {

    @Autowired
    private CountryMapper countryMapper;

    public static void main(String[] args) {
        SpringApplication.run(SampleXmlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PageHelper.startPage(1, 20);
        List<Country> countries = countryMapper.selectAll();
        System.out.println("Total: " + ((Page) countries).getTotal());
        for (Country country : countries) {
            System.out.println("Country Name: " + country.getCountryname());
        }
    }

}
