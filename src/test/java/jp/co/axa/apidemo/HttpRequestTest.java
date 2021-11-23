package jp.co.axa.apidemo;

import jp.co.axa.apidemo.entities.Employee;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void getShouldReturnEmpty() throws Exception {
        String getUrl = "http://localhost:" + port + "/api/v1/employees/";
        ResponseEntity<List<Employee>> response = restTemplate.exchange(
                getUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>(){});
        List<Employee> body = response.getBody();
        assertThat(body).hasSize(0);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }
    @Test
    public void getShouldReturnOneEmployee() throws Exception {
        jdbcTemplate.execute("INSERT INTO EMPLOYEE ( id, EMPLOYEE_NAME ,EMPLOYEE_SALARY,DEPARTMENT) VALUES ( '1', 'jack',1000,'test department' )");

        String getUrl = "http://localhost:" + port + "/api/v1/employees/";
        ResponseEntity<List<Employee>> response = restTemplate.exchange(
                getUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>(){});
        List<Employee> body = response.getBody();
        assertThat(body).hasSize(1);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        jdbcTemplate.execute("DELETE FROM EMPLOYEE WHERE id='1'");
    }
    @Test
    public void PostShouldReturnDefaultNull() throws Exception {
        String getUrl = "http://localhost:" + port + "/api/v1/employees/";
        String postUrl = getUrl;


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("id", 1);
        personJsonObject.put("name", "John");
        personJsonObject.put("salary", 1000);
        personJsonObject.put("department", "tech department");
        HttpEntity<String> request =
                new HttpEntity<String>(personJsonObject.toString(), headers);
        ResponseEntity<ResponseEntity> responseEntity = restTemplate.postForEntity(postUrl,
                request,
                ResponseEntity.class);
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isNull();
        // clear inserted data.
        jdbcTemplate.execute("DELETE FROM EMPLOYEE WHERE id='1'");
    }
    @Test
    public void GetShouldReturnId2Employees() throws Exception {
        String getUrl = "http://localhost:" + port + "/api/v1/employees/2";
        jdbcTemplate.execute("INSERT INTO EMPLOYEE ( id, EMPLOYEE_NAME ,EMPLOYEE_SALARY,DEPARTMENT) VALUES ( '2', 'jack',1000,'test department' )");

        ResponseEntity<Employee> getResponseEntity = this.restTemplate.getForEntity(getUrl, Employee.class);
        Employee body = getResponseEntity.getBody();
        assertThat(body.getId()).isEqualTo(2);
        assertThat(getResponseEntity.getStatusCode().value()).isEqualTo(200);
        jdbcTemplate.execute("DELETE FROM EMPLOYEE WHERE id='2'");
    }

    @Test
    public void DeleteId2EmployeeGetShouldReturnObjectWithNothing() throws Exception {
        // Delete employee with id 2
        String deleteUrl = "http://localhost:" + port + "/api/v1/employees/2";
        jdbcTemplate.execute("INSERT INTO EMPLOYEE ( id, EMPLOYEE_NAME ,EMPLOYEE_SALARY,DEPARTMENT) VALUES ( '2', 'jack',1000,'test department' )");
        this.restTemplate.delete(deleteUrl);

        // Get should return nothing
        String getUrl = "http://localhost:" + port + "/api/v1/employees/2";

        ResponseEntity<Employee> getResponseEntity = this.restTemplate.getForEntity(getUrl, Employee.class);
        Employee body = getResponseEntity.getBody();
        assertThat(body.getId()).isNull();
        assertThat(body.getName()).isNull();
        assertThat(body.getSalary()).isNull();
        assertThat(body.getDepartment()).isNull();
        assertThat(getResponseEntity.getStatusCode().value()).isEqualTo(500);
        jdbcTemplate.execute("DELETE FROM EMPLOYEE WHERE id='2'");
    }
    @Test
    public void UpdatedEmployeeShouldReturnUpdatedInfo() throws Exception {
        // Delete employee with id 2
        String deleteUrl = "http://localhost:" + port + "/api/v1/employees/2";
        jdbcTemplate.execute("INSERT INTO EMPLOYEE ( id, EMPLOYEE_NAME ,EMPLOYEE_SALARY,DEPARTMENT) VALUES ( '2', 'Jack',1000,'tech department' )");
        Employee newEmployeeInfo = new Employee((long) 2, "Jack", 1500, "business department");
        this.restTemplate.put(deleteUrl,newEmployeeInfo);

        // Get should return nothing
        String getUrl = "http://localhost:" + port + "/api/v1/employees/2";

        ResponseEntity<Employee> getResponseEntity = this.restTemplate.getForEntity(getUrl, Employee.class);
        Employee body = getResponseEntity.getBody();
        assertThat(body.getSalary()).isEqualTo(1500);
        assertThat(body.getDepartment()).isEqualTo("business department");
        assertThat(getResponseEntity.getStatusCode().value()).isEqualTo(200);
        jdbcTemplate.execute("DELETE FROM EMPLOYEE WHERE id='2'");
    }
}
