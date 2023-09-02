package com.masonwang.pnp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masonwang.pnp.entity.Proposal;
import com.masonwang.pnp.entity.Team;
import com.masonwang.pnp.entity.User;
import com.masonwang.pnp.repository.ProposalRepository;
import com.masonwang.pnp.repository.TeamRepository;
import com.masonwang.pnp.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
//@SpringBootTest
class PnpApplicationTests {
    //todo repositories not working
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ProposalRepository proposalRepository;
    @MockBean
    UserRepository userRepository;
    @MockBean
    TeamRepository teamRepository;

    private Proposal[] proposals;

    private User[] users;

    private Team[] teams;

    @BeforeEach
    void setup() {

        proposals = new Proposal[]{
                new Proposal("project1"),
                new Proposal("project2"),
                new Proposal("project3"),
                new Proposal("project4"),
                new Proposal("project5"),
                new Proposal("project6"),
        };

        users = new User[]{
                new User("user1", "Qwe12345!", "discord: xxxxx"),
                new User("user2", "Qwe12345!", "discord: xxxxx"),
                new User("user3", "Qwe12345!", "discord: xxxxx"),
                new User("user4", "Qwe12345!", "discord: xxxxx"),
                new User("user5", "Qwe12345!", "discord: xxxxx"),
                new User("user6", "Qwe12345!", "discord: xxxxx"),
                new User("user7", "Qwe12345!", "discord: xxxxx"),
                new User("user8", "Qwe12345!", "discord: xxxxx"),
                new User("user9", "Qwe12345!", "discord: xxxxx"),
        };

        teams = new Team[]{
                new Team("team1"),
                new Team("team2"),
                new Team("team3"),
                new Team("team4"),
                new Team("team5"),
                new Team("team6"),
                new Team("team7"),
                new Team("team8"),
                new Team("team9"),
        };

        for (int i = 0; i < users.length; i++) {
            users[i].setId(i + 1L);
            userRepository.save(users[i]);
        }

        for (int i = 0; i < teams.length; i++) {
            teams[i].setId(i + 1L);
            teams[i].setUsers(new HashSet<>());
            teams[i].getUsers().add(users[i]);
            users[i].setTeams(new HashSet<>());
            users[i].getTeams().add(teams[i]);
            teamRepository.save(teams[i]);
        }

        for (int i = 0; i < proposals.length; i++) {
            proposals[i].setId(i + 1L);
            proposals[i].setUser(users[i]);
            proposals[i].setTeam(teams[i]);
            proposalRepository.save(proposals[i]);
        }
    }

    @AfterEach
    void cleaUp() {
        proposalRepository.deleteAll();
        userRepository.deleteAll();
        teamRepository.deleteAll();
        proposals = null;
        users = null;
        teams = null;
        System.gc();
    }

    //Proposal Controller tests =====================================================

    @Test
    void getProposalTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/proposal/1");
        System.out.println(proposals[0].getName());
        System.out.println(proposalRepository.count());
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(proposals[0].getName()));
    }

    @Test
    void getProposalTestFail() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/proposal/500");
        mockMvc.perform(request).andExpect(status().isNotFound());
    }

    @Test
    void saveProposalTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/proposal/user/1/team/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Proposal("new P")));
        mockMvc.perform(request).andExpect(status().isCreated());
    }

    @Test
    void saveProposalTestFail() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/proposal/user/1/team/20")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Proposal("new P")));
        mockMvc.perform(request).andExpect(status().isNotFound());

        request = MockMvcRequestBuilders.post("/proposal/user/1/team/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("xxxxxxxxx");
        mockMvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    void deleteProposalTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/proposal/3");
        mockMvc.perform(request).andExpect(status().isNoContent());

    }

    @Test
    void deleteProposalFail() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/proposal/50");
        mockMvc.perform(request).andExpect(status().isNoContent());
    }

    //Proposal Controller tests END =================================================


    //User Controller tests =========================================================
    //User Controller tests END =====================================================


    //Team Controller tests =========================================================
    //Team Controller tests END =====================================================
}
