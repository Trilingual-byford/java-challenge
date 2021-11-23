package jp.co.axa.apidemo;


import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(EmployeeController.class)
public class WebMockTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService service;


    @Test
    public void retrieveEmployeesShouldReturnEmployeeList() throws Exception{
        List<Employee> employees=new ArrayList<>();
        employees.add(new Employee((long) 1,"Jack",1000,"Tech department"));
        employees.add(new Employee((long) 2,"Lucy",1000,"Business department"));
        String expectedResult="[{\"id\":1,\"name\":\"Jack\",\"salary\":1000,\"department\":\"Tech department\"},{\"id\":2,\"name\":\"Lucy\",\"salary\":1000,\"department\":\"Business department\"}]";
        when(service.retrieveEmployees()).thenReturn(employees);
        String contentAsString = this.mockMvc.perform(get("/api/v1/employees")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertThat(contentAsString.equals(expectedResult)).isTrue();
    }

}
