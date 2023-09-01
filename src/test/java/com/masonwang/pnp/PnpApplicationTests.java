package com.masonwang.pnp;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class PnpApplicationTests {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	ProposalRepository proposalRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	TeamRepository teamRepository;

	private Proposal[] proposals;

	private User[] users;

	private Team[] teams;

	@BeforeEach
	void setup() {
		proposals = new Proposal[] {
				new Proposal("project1"),
				new Proposal("project2"),
				new Proposal("project3"),
				new Proposal("project4"),
				new Proposal("project5"),
				new Proposal("project6"),
		};

		users = new User[] {
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

		teams = new Team[] {
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

		for (User u : users) {
			userRepository.save(u);
		}

		for (int i = 0; i < teams.length; i++) {
			teams[i].getUsers().add(users[i]);
			users[i].getTeams().add(teams[i]);
			teamRepository.save(teams[i]);
		}

		for (Proposal p : proposals) {
			proposalRepository.save(p);
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
}
