package com.devops.bugtracker;

import com.devops.bugtracker.model.Bug;
import com.devops.bugtracker.service.BugService;
import com.devops.bugtracker.controller.BugController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BugController.class)
public class BugControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BugService bugService;

    @Test
    public void testGetAllBugs() throws Exception {
        Bug bug = new Bug();
        bug.setTitle("Login failure");
        bug.setPriority(Bug.Priority.HIGH);
        bug.setStatus(Bug.Status.OPEN);

        Mockito.when(bugService.getAllBugs()).thenReturn(List.of(bug));

        mockMvc.perform(get("/api/bugs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Login failure"));
    }

    @Test
    public void testCreateBug() throws Exception {
        Bug bug = new Bug();
        bug.setId(1L);
        bug.setTitle("NullPointerException");
        bug.setPriority(Bug.Priority.CRITICAL);
        bug.setStatus(Bug.Status.OPEN);

        Mockito.when(bugService.createBug(Mockito.any())).thenReturn(bug);

        mockMvc.perform(post("/api/bugs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"NullPointerException\",\"priority\":\"CRITICAL\",\"status\":\"OPEN\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("NullPointerException"));
    }
}